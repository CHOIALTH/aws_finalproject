package dbsecure;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration //내가 설정 클래스입니다
@EnableEncryptableProperties //현재 정보를 application.properties파일에서 사용 가능
public class DBConfig {
	@Bean("jasyptEncryptor") //메소드 리턴 객체 주입
	public StringEncryptor  stringEncryptor(){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(System.getenv("DB_PASSWORD"));

        config.setAlgorithm("PBEWithMD5AndDES"); // 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        System.out.println("===DBConfig 출력===");
        System.out.println(System.getenv("DB_PASSWORD"));//1234
        System.out.println
        (encryptor.decrypt("ql4WFvaCj7EvG4VN2ip51sZ82q6oXgBNXyZccmJ2ooM="));
        System.out.println
        (encryptor.decrypt("Xfl1kXNmvZjcXMg2oJfcwgtrvP7XsWkbf2y99OSgU5FJxARlQwKz5rmPBzvu+8RSlUOz75/O7sYOevafO4QwuovyKpUFQ2v4u8pLMiZXIH0HLpDYDrdDsYoHHwg5zio6/VVd/47hHCM=")
        );
        System.out.println(encryptor.decrypt("KD8I1AG5Q2gr7TX69l59SKVcoMhEwKIu"));
        System.out.println(encryptor.decrypt("dVMIlZHDwFfLwFZkrjXOmWP7CT1Tqwjf"));

        return encryptor;
        
        
	}
}
