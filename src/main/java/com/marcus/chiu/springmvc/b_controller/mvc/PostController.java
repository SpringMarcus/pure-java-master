package com.marcus.chiu.springmvc.b_controller.mvc;

import com.marcus.chiu.springmvc.a_bean.BeanExample;
import com.marcus.chiu.springmvc.c_service.service.PostService;
import com.marcus.chiu.springmvc.e_entity.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by marcus.chiu on 10/21/16.
 */
@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    /**
     *
     * @param modelMap
     * @return String
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPosts(ModelMap modelMap) {
        List<Post> posts = postService.findAllPosts();
        modelMap.addAttribute("posts", posts);
        return "allposts";
    }

    /**
     * This method will delete an employee by its SSN value
     * @param postId - @PathVariable indicates this parameter will be bound to variable
     *            in URI template
     * @return String
     */
    @RequestMapping(value = { "/delete-{postId}-post" }, method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable int postId) {
        postService.deletePostById(postId);
        return "redirect:/post/list";
    }
}
