/**
 * 
 */

var forum = angular.module('forumModule',[]);

forum.factory('forumFactory',['$http','$q',
	
	function($http, $q) {
	
	var forumUrl = 'http://localhost:2222/theBackendProject';
	
	return {
		addForum : addForum,
		viewForum : viewForum,
		forumlist : forumlist,
		joinforum : joinforum
        
	};
	
	function addForum(forum) {
        var deferred = $q.defer();
        console.log('add forum method in factory'+user);
        $http.post(forumUrl + '/forum/new', forum).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add forum mehod in factory');
        return deferred.promise;
    }
	
	// list of forums
	function forumlist() {
        console.log('Inside forum list in factory now');
       var deferred = $q.defer();
       $http.get(forumUrl + '/forum/list')
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of forum list mehod in factory');
           return deferred.promise;
   }
	
	//Function for viewing single forum using forumid as a parameter
    function viewForum(id) {
        console.log('Inside factory now forum single');
        var deferred = $q.defer();

        $http.get(forumUrl + '/forum/' + id)
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
	
    // join a forum
	function joinforum(fmember) {
        console.log('Inside forum join method in factory now');
       var deferred = $q.defer();
       $http.post(forumUrl + '/fmember/new', fmember)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of forum join mehod in factory');
           return deferred.promise;
   }
    
    /*********************/
	}]);