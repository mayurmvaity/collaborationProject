/**
 * 
 */

login.controller('loginController', ['loginFactory','$routeParams', '$location', '$route', '$rootScope',
	function(loginFactory, $routeParams, $location, $route, $rootScope) {
	
	var self = this;
    self.credentials = {};
    self.error = false;
    self.authError = false;
	
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
                } else {
                    debugger;
                    loginFactory.setUserIsAuthenticated(true);
                     console.log(user);
                     loginFactory.setRole(user.role);
                     $rootScope.authenticated = true;
                     $rootScope.message = "Welcome" + user.fname;
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
	
}]);