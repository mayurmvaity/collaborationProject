/**
 * 
 */

var fpost = angular.module('fpostModule', []);

fpost.factory('fpostFactory', ['$http', '$q', function($http, $q){

	var fpostUrl = 'http://localhost:2222/theBackendProject';
	
	return {
		addFpost : addFpost,
		fpostsByForumid : fpostsByForumid
        
	};
	
	// add forum post method
	function addFpost(forumpost) {
        var deferred = $q.defer();
        //console.log('add forum post method in factory'+user);
        $http.post(fpostUrl + '/fpost/new', forumpost).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add forum post mehod in factory');
        return deferred.promise;
    }
	
	// list of fposts by forumid
	function fpostsByForumid(forumid) {
        console.log('Inside fpost list in factory now');
       var deferred = $q.defer();
       $http.get(fpostUrl + '/fpost/list/' + forumid)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of fpost list mehod in factory');
           return deferred.promise;
   }
	
	/*************************/
	
}]);

