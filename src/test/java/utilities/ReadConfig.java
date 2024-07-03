package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadConfig
{
    Properties pro;

    public ReadConfig() throws FileNotFoundException
    {
        File file = new File("/Users/luckythecoder/IdeaProjects/InternetBankingProject/src/test/resources/config.properties");
        try
        {
            FileInputStream fi = new FileInputStream(file);
            pro = new Properties();
            pro.load(fi);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String getApplicationURL()
    {
        String url = pro.getProperty("baseURL");
        return url;
    }

    public String getUsername()
    {
        String un = pro.getProperty("username");
        return un;
    }

    public String getPassword()
    {
        String pwd  =pro.getProperty("password");
        return pwd;
    }
}
