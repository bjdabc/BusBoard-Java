package training.busboard.web;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import training.busboard.Bus;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }
    
    

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
    	//HttpServletRequest request, HttpServletResponse response,
        //ServletContext servletContext, TemplateEngine templateEngine)

    	//BusInfo busInfo = new BusInfo(postcode);
    	//ArrayList<Bus> bus_list = busInfo.getBusList();
    	
    	//WebContext ctx = new WebContext(request, servletContext, request.getLocale());
        //ctx.setVariable("prods", allProducts);

        //templateEngine.process("product/list", ctx, response.getWriter());
    	
        return new ModelAndView("info", "busInfo", new BusInfo(postcode)) ;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}