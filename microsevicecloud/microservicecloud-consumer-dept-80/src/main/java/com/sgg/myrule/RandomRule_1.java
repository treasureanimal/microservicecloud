package com.sgg.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 依旧轮询策略，每个服务器要求被调用5次，如果超过所有的服务器，将重新开始调用
 */
public class RandomRule_1 extends AbstractLoadBalancerRule {

    private int total = 0;    //总共被调用的次数，目前要求每台被调用5次
    private int currentIndex = 0;//当前提供服务的机器号

    public Server choose(ILoadBalancer lb, Object key) { //lb指的是有多少个需要负载均衡的服务器
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers(); //选择哪一台服务器
            List<Server> allList = lb.getAllServers(); //获取到所有的服务器

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }


            if(total < 5) //如果调用次数小于5
            {
                server = upList.get(currentIndex);//继续获取当前的机器
                total++;
            }else {                                 //如果调用次数大于5
                total = 0;                          //机器加1下一台
                currentIndex++;
                if(currentIndex >= upList.size())
                {
                    currentIndex = 0;
                }

            }
            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

}