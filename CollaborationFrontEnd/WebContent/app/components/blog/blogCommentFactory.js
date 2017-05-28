/**
 * 
 */

var blogComm = angular.module('blogCommentModule', []);

blogComm.factory('blogCommentFactory', ['$http', '$q', 
	
	function($http, $q) {
	
	var blogCommUrl = 'http://localhost:2222/theBackendProject';
	
	return {
        addBlogComm : addBlogComm,
        blogCommentlist : blogCommentlist
        
	};
	
	function addBlogComm(bcomm) {
        var deferred = $q.defer();
        console.log('add blog comment method in factory'+bcomm);
        $http.post(blogCommUrl + '/bcomm/new', bcomm).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add blog comment mehod in factory');
        return deferred.promise;
    }
	
	function blogCommentlist(id) {
        console.log('Inside blog comm list factory now');
       var deferred = $q.defer();
       $http.get(blogCommUrl + '/bcomm/list/' + id)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of blog comm list mehod in factory');
           return deferred.promise;
   }
	
	
}]);

