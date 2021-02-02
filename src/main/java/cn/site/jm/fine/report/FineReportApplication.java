package cn.site.jm.fine.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author JupiterMouse
 * @since 1.0
 */
@SpringBootApplication
public class FineReportApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(FineReportApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
