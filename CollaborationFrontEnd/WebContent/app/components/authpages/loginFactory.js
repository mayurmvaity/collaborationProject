/**
 * 
 */

var login = angular.module('loginModule', []);

login.factory('loginFactory', ['$http', '$q', '$cookies', function($http, $q, $cookies){
	
	var loginUrl = 'http://localhost:2222/theBackendProject';
	var role = 'GUEST';
	var userIsAUthenticated = false;
	
	return  {
		saveUser: saveUser,
		loadUserFromCookie : loadUserFromCookie,
        login: login,
        setRole : setRole,
        getRole : getRole,
        setUserIsAuthenticated : setUserIsAuthenticated,
        getUserIsAuthenticated : getUserIsAuthenticated,
        logout : logout,
        checkUsername : checkUsername
        
    };
	
    function setUserIsAuthenticated(value) {

        userIsAuthenticated = value;
    };

    function getUserIsAuthenticated() {

        return userIsAuthenticated;
    };
    
    function setRole(value) {

        role = value;
    };

    function getRole() {

        return role;
    };
    
  //Loading user from cookies
    function loadUserFromCookie() {
        user = $cookies.getObject('user');
        if (user) {
            userIsAuthenticated = true;
            role = user.role;
        } else {
           userIsAuthenticated = false;
            role = 'GUEST';
        }
        return user;
    };

    //saving user inside cookies
    function saveUser(user) {
       // debugger;
        $cookies.putObject('user', user);
        role = user.role;
       userIsAuthenticated = true;

    };
    
	//Method for user login
    function login(credentials) {
        var deferred = $q.defer();
        $http.post(loginUrl + '/login', credentials).then (
            function(response) {
                console.log('Success in login method');
                deferred.resolve(response.data);
            }, function (error) {
            	console.log('Error in login method');
                deferred.reject(error);
            }
        );
        return deferred.promise;
    }   
    
  //Method for user logout
    function logout(user) {
        // debugger;
        var deferred = $q.defer();
        $http.post(loginUrl + '/logout', user).then(
            function (response) {
                $cookies.putObject('user', undefined);
                userIsAuthenticated = false;
                role = 'GUEST';
                deferred.resolve(response);
             //   Materialize.toast('Logout successfully!', 2000);
            });
        return deferred.promise;
    }

    //Method for checking username
    function checkUsername(username) {

        var deferred = $q.defer();
        $http.post(loginUrl + '/checkuser', username).then (
            function(response) {    
                console.log('In checkuser method factory');
                deferred.resolve(response);
            }, function(error) {
                console.log('Failed');
                deferred.resolve(error);
            }
        );
        return deferred.promise;
    };
    
    // end of everything
}]);