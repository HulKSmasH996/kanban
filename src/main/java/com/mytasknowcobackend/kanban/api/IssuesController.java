package com.mytasknowcobackend.kanban.api;

import com.mytasknowcobackend.kanban.model.Issues;
import com.mytasknowcobackend.kanban.service.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
@RestController
public class IssuesController {

	private final IssuesService issuesService;
    @Autowired
    public IssuesController(IssuesService issuesService) {
        this.issuesService = issuesService;
    }

  //  @ApiOperation(value = "Create an issue")
    @PostMapping
    public void addIssue(@RequestBody Issues issue) {
		/*
		 * if(user.isValidEmail(user.getEmail())) userService.addUser(user); else throw
		 * new Exception(result.toString());
		 */
    	issuesService.addIssue(issue);
    }
    
    @GetMapping
    public List<Issues> returnAllIssues(){
    	return issuesService.returnAllIssues();
    }


}
