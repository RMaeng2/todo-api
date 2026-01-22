package com.example.todo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI todoOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(apiServers())
                .externalDocs(externalDocumentation());
    }

    private Info apiInfo() {
        return new Info()
                .title("Todo API")
                .description("""
                        ## Todo 관리 REST API
                        
                        이 API는 할 일(Todo) 관리를 위한 RESTful 서비스입니다.
                        
                        ### 주요 기능
                        - ✅ Todo 생성, 조회, 수정, 삭제 (CRUD)
                        - ✅ Todo 완료 상태 관리
                        - ✅ 전체 Todo 목록 조회
                        
                        ### 인증
                        현재 버전은 인증이 필요하지 않습니다. (개발 환경)
                        
                        ### 응답 형식
                        모든 응답은 JSON 형식입니다.
                        """)
                .version("1.0.0")
                .contact(new Contact()
                        .name("Backend Team")
                        .email("mhjeong0809@gmail.com")
                        .url("https://github.com/RMaeng2/todo-api"))
                        .license(new License()
                        .name("MIT License")
                        .url("https://opensource.org/licenses/MIT"));
    }

    private List<Server> apiServers() {
        return List.of(
                new Server()
                        .url("http://localhost:8080")
                        .description("로컬 개발 서버"),
                new Server()
                        .url("https://api-dev.example.com")
                        .description("개발 서버 (준비 중)"),
                new Server()
                        .url("https://api.example.com")
                        .description("운영 서버 (준비 중)")
        );
    }

    private ExternalDocumentation externalDocumentation() {
        return new ExternalDocumentation()
                .description("GitHub Repository")
                .url("https://github.com/RMaeng2/todo-api");
    }
}
