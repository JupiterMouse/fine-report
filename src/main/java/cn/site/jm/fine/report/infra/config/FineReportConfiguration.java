package cn.site.jm.fine.report.infra.config;

import java.io.File;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 帆软内嵌tomcat配置类
 * </p>
 *
 * @author JupiterMouse
 * @since 1.0
 */
@Configuration
public class FineReportConfiguration {

    @Bean
    FineReportWebServerFactoryCustomizer fineReportWebServerFactoryCustomizer() {
        return new FineReportWebServerFactoryCustomizer();
    }

    /**
     * 自定义帆软webapp目录，从src/main/webapp重定向到自定义的目录下
     */
    @SuppressWarnings("unused")
    public static class FineReportWebServerFactoryCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>, Ordered {

        @Override
        public void customize(TomcatServletWebServerFactory factory) {
            String docBase = System.getProperty("DOC_BASE");
            if (StringUtils.isEmpty(docBase)) {
                throw new IllegalArgumentException("DOC_BASE system property can't empty");
            }
            File file = new File(docBase);
            if (!file.exists()) {
                boolean mkdirsStatus = file.mkdirs();
            }
            if (file.exists() && file.isDirectory()) {
                String env = file.getAbsoluteFile().getPath();
                factory.setDocumentRoot(file);
            } else {
                throw new IllegalArgumentException("Fine webapp dir [env] not found");
            }
            // 加载帆软Web
            factory.getTomcatContextCustomizers()
                    .add(context -> context.addApplicationListener("com.fr.startup.FineServletContextListener"));
        }

        @Override
        public int getOrder() {
            return 0;
        }
    }

}
