package club.xyes.zkh.retail.web.front;

import club.xyes.zkh.retail.commons.context.ApplicationConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Create by 郭文梁 2019/5/16 0016 15:43
 * RetailWebFrontApplication
 * 前台项目入口
 *
 * @author 郭文梁
 * @data 2019/5/16 0016
 */
@SpringBootApplication(scanBasePackages = ApplicationConstants.Context.COMPONENT_PACKAGE)
@MapperScan(ApplicationConstants.Context.MAPPER_PACKAGE)
public class RetailWebFrontApplication {
    /**
     * 启动方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(RetailWebFrontApplication.class, args);
    }
}
