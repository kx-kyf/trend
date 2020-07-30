package cn.how2j.trend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author 不休乔木
 * @version 1.0
 * @package cn.how2j.trend.web*
 * @since 1.0
 */
@Controller
public class ViewController {

    @GetMapping("/")
    public String view() throws Exception {
        return "view";
    }
}
