/**
 * 
 */
var user = angular.module('userModule',[]);

//change MyModule with your module name used within the application
user.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            console.log(model);
            var modelSetter = model.assign;
            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);


user.factory('userFactory',['$http','$q', '$rootScope', '$routeParams',
	
	function($http, $q, $rootScope, $routeParams) {
	
	var userUrl = 'http://localhost:2222/theBackendProject';
	
	return {
		addUser : addUser,
		fetchUser : fetchUser,
		userlist : userlist,
		usernalist : usernalist,
		userAppr : userAppr,
		userapprvlist : userapprvlist,
		viewUser : viewUser,
		uploadFile : uploadFile
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
	
    // uploadFile function to upload the image on the server
    function uploadFile(file) {
            var deferred = $q.defer();
           
            var fd = new FormData();
            fd.append('file', file);
           fd.append('id', $rootScope.user.userid);
           $http.post(userUrl + '/upload/profile-picture', fd, {
           transformRequest: angular.identity,
           headers: { 'Content-Type': undefined }
          })
           .then(
               function (response) {
               deferred.resolve(response.data);
           },
               function (error) {
               console.log(error);
               deferred.reject(error);
           }
       );
     return deferred.promise;
    }
	
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
    
    //Function for viewing single user using userid as a parameter
    function viewUser(id) {
        console.log('Inside user factory now');
        var deferred = $q.defer();

        $http.get(userUrl + '/user/' + id)
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