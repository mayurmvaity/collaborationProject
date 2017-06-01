/**
 * 
 */
var user = angular.module('userModule',[]);

user.factory('userFactory',['$http','$q',
	
	function($http, $q) {
	
	var userUrl = 'http://localhost:2222/theBackendProject';
	
	return {
		addUser : addUser,
		fetchUser : fetchUser,
		userlist : userlist,
		usernalist : usernalist,
		userAppr : userAppr,
		userapprvlist : userapprvlist
        
	};
	
	function addUser(user) {
        var deferred = $q.defer();
        console.log('add user method in factory'+user);
        $http.post(userUrl + '/user/new', user).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add user mehod in factory');
        return deferred.promise;
    };
	
	
	
	//function to fetch user and user detail
	function fetchUser(id) {
	     var deferred = $q.defer();
	
	      $http.get(userUrl + '/user/'+ id)
	        .then (
	            function(response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse) {
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	
	};
	
	//Function to fetch uers list
    function userlist() {
         console.log('Inside factory now');
        var deferred = $q.defer();

        $http.get(userUrl + '/user/list')
            .then (
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
    }
    
  //Function to fetch uers not approved list
    function usernalist() {
         console.log('Inside factory now');
        var deferred = $q.defer();

        $http.get(userUrl + '/user/nalist')
            .then (
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
    }
    
    
    // function to approve user
    function userAppr(id) {
        var deferred = $q.defer();
        console.log('Approve method start');
        $http.post(userUrl + '/user/approv/' + id).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        console.log('Approve method end');
        return deferred.promise;
    }
    
    //Function to fetch users approved list
    function userapprvlist() {
         console.log('Inside factory now');
        var deferred = $q.defer();

        $http.get(userUrl + '/user/aplist')
            .then (
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
    }
    
    /*******************/
}]);