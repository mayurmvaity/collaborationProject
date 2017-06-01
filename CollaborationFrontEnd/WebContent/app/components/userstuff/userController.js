/**
 * 
 */
user.controller('userController',['userFactory', 'loginFactory','$routeParams', '$location', '$route', '$rootScope',
	function(userFactory, loginFactory, $routeParams, $location, $route, $rootScope) {
	
	var self = this;
	
	self.user1 = loginFactory.loadUserFromCookie();
	
	self.user = {
			
			userid : null,
			fname : '',
			lname : '',
			email : '',
			pw : '',
			pno : '',
			add1 : '',
			add2 : '',
			add3 : '',
			city : '',
			state : '',
			pincode : '',
			role: 'User',
			active : 'Y'
	}
	
	//function for adding a new blog
    self.addUser = function () {

        console.log('in user controller');
         //calling the addBlog method in the factory
        userFactory.addUser(self.user)
            .then (
                function(user) {
                    self.user =  user;
                    var bId = self.user.userid;
                    $location.path('/home');
                }, function (errResponse) {
                }
            );
         console.log('end of user controller');
    }
	
	//function to fetch user and user detail
    self.fetchUser = function() {
       
        var id = $routeParams.userId;
         userFactory.fetchUser(id)
               .then (
                   function(user1) {
                       debugger;
                       self.user1 = user1;
                                              
                   },
                   function(errResponse) {
                       
                   }
               );
    }
	
    userlist();
    
    //Function to view list of all jobs
    function userlist() {
    	userFactory.userlist()
            .then (
                function(users) {   
                    self.userlist = users;
                   
                    console.log(self.userlist);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }
    
    // calling usernalist function
    usernalist();
    
    //Function to view list of all unapproved users
    function usernalist() {
    	userFactory.usernalist()
            .then (
                function(users) {   
                    self.usernalist = users;
                   
                    console.log(self.userlist);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }
    
  //Function to approve a user
    self.userAppr = function(id) {      
    	userFactory.userAppr(id)
            .then (
                function(user) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
    // calling userapprvlist function
    userapprvlist();
    
    //Function to view list of all approved users
    function userapprvlist() {
    	userFactory.userapprvlist()
            .then (
                function(users) {   
                    self.userapprvlist = users;
                   
                    console.log(self.userapprvlist);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }
    
    /**************************/
}]);