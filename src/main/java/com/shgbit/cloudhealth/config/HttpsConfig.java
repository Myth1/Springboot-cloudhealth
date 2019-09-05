/*
package com.shgbit.cloudhealth.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpsConfig {
    // 监听的http请求的端口 http.port=端口号   如8080
    @Value("${http.port}")
    Integer httpPort;

    //https端口 如443
    @Value("${server.port}")
    Integer httpsPort;

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        //表示用 8080 端口来供http访问
        connector.setScheme("http");
        connector.setPort(httpPort);
        connector.setSecure(false);
        //自动重定向到 https 端口 默认为8443 这里设置为443 因为与SVN Server 端口冲突了
        connector.setRedirectPort(httpsPort);
        return connector;
    }

}
*/
