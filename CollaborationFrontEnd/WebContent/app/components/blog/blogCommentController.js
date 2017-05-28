/**
 * 
 */
blogComm.controller('blogCommentController', ['blogCommentFactory', '$routeParams', '$location', '$route', '$rootScope', 
	function(blogCommentFactory, $routeParams, $location, $route, $rootScope) {

	var self = this;
	
	self.bcomm = {
			
			blogcommid : null,
			commdata : '',
			active : 'Y'
	}
	
	//function for adding a new blog
    self.addBlogComm = function () {
    	
    	self.bcomm.user = $rootScope.user;
    	self.bcomm.commdate = new Date();
        console.log('in controller');
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
	
	
}]);

