/**
 * 
 */
forum.controller('forumController',['forumFactory','$routeParams', '$location', '$route', '$rootScope', '$scope',
	function(forumFactory,$routeParams, $location, $route, $rootScope, $scope) {
	
	var self = this;
	
	self.forum = {
			
			forumid : null,
			ftitle: '',
			fdata: '',
			active : 'Y'
	}
	
	self.fmember = {
			
			fmemberid : null,
			active : 'Y',
			isApproved : 'N'
	}
	
	//function for adding a new blog
    self.addForum = function () {

		self.forum.user = $rootScope.user;
		self.forum.fdate = new Date();
		
        console.log('in forum controller');
         //calling the addBlog method in the factory
        forumFactory.addForum(self.forum)
            .then (
                function(forum) {
                    self.forum =  forum;
                    var bId = self.forum.forumid;
                    $location.path('/forum/' + bId);
                }, function (errResponse) {
                }
            );
         console.log('end of user controller');
    }
	
	// calling method 
	forumlist();
    
	// forum list method
    function forumlist() {
    	
    	console.log('inside forum list method');
    	forumFactory.forumlist()
           .then (
               function(forums) {   
                   self.forumlist = forums;
                   console.log(self.forumlist);
               },
               function(errResponse) {
                   console.log('forum list Failure!');
               }
           );
        console.log('End of forum list method');
   }
	
    // for viewing single forum
	self.singleForum = {};
    
    //function for viewing single job
    self.viewForum = function() {
        //Assigning blog id to variable blogId
        var blogId = $routeParams.id;
        
        forumFactory.viewForum(blogId)
            .then (
                function(forum) {
                    self.singleForum = forum;
                    $scope.vforum = forum;
                },
                function(errResponse) {
                }
            );

    } 
	
    
  //function for joining a forum
    self.joinforum = function (frm) {

		self.fmember.user = $rootScope.user;
		self.fmember.forum = frm;
		
        console.log('in join forum method controller');
         //calling the addBlog method in the factory
        forumFactory.joinforum(self.fmember)
            .then (
                function(resp) {
                    self.fmember =  resp;
                    $route.reload();
                }, function (errResponse) {
                }
            );
         console.log('end of join forum method controller');
    }
    
    /***********************/
}]);