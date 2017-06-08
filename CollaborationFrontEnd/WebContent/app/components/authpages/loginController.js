/**
 * 
 */

login.controller('loginController', ['loginFactory', 'userFactory', '$routeParams', '$location', '$route', '$rootScope',
	function(loginFactory, userFactory, $routeParams, $location, $route, $rootScope) {
	
	var self = this;
    self.credentials = {};
    self.error = false;
    self.authError = false;
	
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
    
	 //Method for user login 
    self.login = function() {
        debugger;
        loginFactory.login(self.credentials).then (

            function (user) {
                debugger;

                if(self.credentials.email == null || self.credentials.pw == null) {
                    self.error = true;
                    $rootScope.message = "Please provide both username and password";
                }
                else if(user.email == null || user.pw == null) {
                    self.error = true;
                    $rootScope.message = "Incorrect username or password";
                } else if(user.isApproved == 'N') {
                    self.error = true;
                    $rootScope.message = "Apparently your registeration request is not approved yet!";
                } else if(user.isApproved == 'R') {
                    self.error = true;
                    $rootScope.message = "Your registeration request has been rejected!";
                } else {
                   // debugger;
                    loginFactory.setUserIsAuthenticated(true);
                     console.log(user);
                     loginFactory.setRole(user.role);
                     $rootScope.authenticated = true;
                     $rootScope.message = "Welcome" + user.fname;
                     Materialize.toast('Welcome ' + user.fname + ' ' + user.lname, 4000)
                     $rootScope.userId = user.userid;
                     loginFactory.saveUser(user);
                      switch(user.role) {
                      	case 'Admin' :
	                          self.isAdmin = true;
	                          $location.path('/profile/' + $rootScope.userId);
	                          break;
                        case 'User' :
                            self.isUser = true;
                            $location.path('/profile/' + $rootScope.userId);
                            break;
                        default :
                            $location.path('/home');
                    }
                    $rootScope.isLogin = true;
                }   
            },
                function(error) {
                    // AuthenticationFactory.setUserIsAuthenticated(false);
                     $rootScope.authenticated = false;
                     self.error = true;
                });
    }
// end of login method
	
  //function for adding a new blog
    self.addUser = function () {

        console.log('in user controller');
         //calling the addBlog method in the factory
        userFactory.addUser(self.user)
            .then (
                function(user) {
                    // self.user =  user;
                   // var bId = self.user.userid;
                	Materialize.toast('Registered successfully!', 4000)
                	$route.reload();
                    // $location.path('/home');
                }, function (errResponse) {
                }
            );
         console.log('end of user controller');
    }
    
    // end of everything
}]);