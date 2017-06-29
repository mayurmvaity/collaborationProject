/**
 * 
 */

blog.controller('blogController',['blogFactory', 'blogCommentFactory', '$routeParams', '$location', '$route', '$rootScope', '$scope',
	function(blogFactory, blogCommentFactory,$routeParams, $location, $route, $rootScope, $scope) {
	
	var self = this;
	
	
	
	self.blog = {
			
			blogid : null,
			btitle : '',
			bdata : '',
			blikes : '0',
			bcomments: '0',
			active : 'Y'
	}
	
	self.bcomm = {
			
			blogcommid : null,
			commdata : '',
			active : 'Y'
	}
	
	// variable to hold the list 
	self.bloglist = [];
	
	// for viewing single blog
	self.singleBlog = {};
	
	self.myblogCount = [];
	
	//function for adding a new blog
    self.addBlog = function () {
    	
    	self.blog.user = $rootScope.user;
    	self.blog.bdate = new Date();
        console.log('in controller');
         //calling the addBlog method in the factory
         blogFactory.addBlog(self.blog)
            .then (
                function(blog) {
                    self.blog =  blog;
                    Materialize.toast('Blog created successfully', 4000);
                    var bId = self.blog.blogid
                    $location.path('/blog/' + bId);
                }, function (errResponse) {
                }
            );
         console.log('end of controller');
    }
	
    //function for editing blog
    self.editBlog = function () {

        console.log('in editBlog method controller');
        var blogId = self.blog.blogid;
        self.blog.bdate = new Date();
        blogFactory.editBlog(self.blog)
            .then (
                function(blog1) {
                    self.blog =  blog1;
                    
                    $location.path('/blog/' + blogId);
                }, function (errResponse) {
                }
            );
         console.log('end of controller editBlog method');
    }
    
    
    bloglist();
    
    function bloglist() {
    	
    	console.log('inside blog list method');
        blogFactory.bloglist()
           .then (
               function(blogs) {   
                   self.bloglist = blogs;
                   
                   console.log(self.bloglist);
               },
               function(errResponse) {
                   console.log('Failure!');
               }
           );
        console.log('End of blog list method');
   }

  //Function to add likes to blog
    self.likeblog = function(id) {      
        blogFactory.likeblog(id)
            .then (
                function(blog) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
    //function for viewing single blog
    self.viewBlog = function() {
        //Assigning blog id to variable blogId
        var blogId = $routeParams.id;
        blogFactory.viewBlog(blogId)
            .then (
                function(blog) {
                    self.singleBlog = blog;
                    self.blog = self.singleBlog;
                    $scope.bid = blog.blogid;
                    blogCommentlist();
                },
                function(errResponse) {
                }
            );

    }
    
    
    // variable to hold the na blog list 
	//self.blognalist = [];
    
    // calling usernalist function
    blognalist();
    
    //Function to view list of all unapproved users
    function blognalist() {
    	blogFactory.blognalist()
            .then (
                function(blogs) {   
                    self.blognalist = blogs;
                   
                    console.log(self.blognalist);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }
    
    
    //Function to approve a blog
    self.blogAppr = function(id) {      
    	blogFactory.blogAppr(id)
            .then (
                function(blog) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
    //Function to disapprove a blog
    self.blogDisAppr = function(id) {      
    	blogFactory.blogDisAppr(id)
            .then (
                function(blog) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
  //function for adding a new blog
    self.addBlogComm = function () {
    	
    	 self.bcomm.user = $rootScope.user;
    	 self.bcomm.commdate = new Date();
    	 self.bcomm.blogid = $scope.bid;
        console.log('in controller');
        console.log('********blogid '+ self.bcomm.blogid);
        console.log('*******blogid of blog'+ self.blog.blogid);
         //calling the addBlog comm method in the factory
        blogCommentFactory.addBlogComm(self.bcomm)
            .then (
                function(bcomm) {
                    self.bcomm =  bcomm;
                    var bId = self.bcomm.blogcommid;
                    $route.reload();
                }, function (errResponse) {
                }
            );
         console.log('end of controller');
    }
    
    
    
    function blogCommentlist() {
    	
    	var blogid = $routeParams.id;
    	console.log('inside blog comm list method');
    	blogCommentFactory.blogCommentlist(blogid)
           .then (
               function(bcomms) {   
                   self.blogCommentlist = bcomms;
                   
                   console.log(self.blogCommentlist);
               },
               function(errResponse) {
                   console.log('Failure!');
               }
           );
        console.log('End of blog comm list method');
   }
    
    userbloglist();
    
    function userbloglist() {
    	
    	var usrid = $rootScope.user.userid;
    	
    	console.log('inside user blog list method');
        blogFactory.userbloglist(usrid)
           .then (
               function(blogs) {   
                   self.userbloglist = blogs;
                   self.myblogCount = self.userbloglist.length;
                   console.log(self.userbloglist);
                   console.log('Success in user blog list');
               },
               function(errResponse) {
                   console.log('Failure!');
               }
           );
        console.log('End of user blog list method');
   }
    
    // fn to delete a blog
    self.deleteABlog = function() {
    	
    	var blogid1 = $routeParams.id;
    	console.log('inside blog delete method');
    	blogFactory.deleteABlog(blogid1)
           .then (
               function(blog) {   
            	 
            	   $location.path('/blog/list');
               },
               function(errResponse) {
                   console.log('blog delete Failure!');
               }
           );
    	
        console.log('End of blog delete method');
   }
    
    // fn to block a blog
    self.blockABlog = function() {
    	
    	var blogid1 = $routeParams.id;
    	console.log('inside blog block method');
    	blogFactory.blockABlog(blogid1)
           .then (
               function(blog) {   
            	 
            	   $location.path('/blog/list');
               },
               function(errResponse) {
                   console.log('blog block Failure!');
               }
           );
    	
        console.log('End of blog block method');
   }
    
    /*************************/
}]);