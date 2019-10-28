import com.midea.EurekaClientApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.Cookie;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2019/10/23 15:00
 * @Modified By：
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={EurekaClientApp.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MockTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 测试 获取当前用户信息
     * @throws Exception
     */
    @Test
    public void mockUserInfoTest() throws Exception {
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/index/userinfo").header("csrfToken",351657722).cookie(new Cookie("AIB_TOKEN","8eab873c7104425cb3d93d39bea38fe8"))).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

        System.err.println(contentAsString);
    }



    /**
     * 测试 获取用户列表
     * @throws Exception
     */
    @Test
    public void mockUserInfoListTest() throws Exception {

        String  param="{\n" +
                "\"name\":\"admin\",\n" +
                "\"password\":\"123456\"\n" +
                "}";

        String contentAsString = mockMvc.perform(
                MockMvcRequestBuilders.post("/login").
                        accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                        .content(param)).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

        System.err.println(contentAsString);

    }



    /**
     * 测试 用户登录日志查询
     * @throws Exception
     */
    @Test
    public void mockloginLogListTest() throws Exception {

        String  param="{\"p\":1,\"c\":20,\"startTime\":\"\",\"endTime\":\"\"}";

        String contentAsString = mockMvc.perform(
                MockMvcRequestBuilders.post("/userTokenLog/queryUserTokenLog").
                        accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).header("csrfToken",351657722).
                        cookie(new Cookie("AIB_TOKEN","8eab873c7104425cb3d93d39bea38fe8")).content(param)).
                andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

        System.err.println(contentAsString);

    }
}
