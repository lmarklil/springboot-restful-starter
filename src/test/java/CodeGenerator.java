import java.util.Scanner;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {

    private static String AUTHOR = "Li Jinhui";
    private static String DATABASE = "seed";
    private static String DATABASE_USERNAME = "root";
    private static String DATABASE_PASSWORD = "root";
    private static String PROJECT = "com.neko.seed";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/"+DATABASE+"?serverTimezone=Asia/Shanghai&useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(DATABASE_USERNAME);
        dsc.setPassword(DATABASE_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(PROJECT);
        mpg.setPackageInfo(pc);

        // 自定义配置
        // InjectionConfig cfg = new InjectionConfig() {
        //     @Override
        //     public void initMap() {
        //         // to do nothing
        //     }
        // };
        // List<FileOutConfig> focList = new ArrayList<>();
        // focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
        //     @Override
        //     public String outputFile(TableInfo tableInfo) {
        //         // 自定义输入文件名称
        //         return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
        //                 + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
        //     }
        // });
        // cfg.setFileOutConfigList(focList);
        // mpg.setCfg(cfg);
        // mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass(PROJECT + ".base.BaseEntity");     // 自定义实体类继承
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass(PROJECT + ".base.BaseController");     // 自定义控制器继承
        strategy.setInclude(scanner("表名"));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}