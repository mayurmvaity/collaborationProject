/**
 * 
 */
var job = angular.module('jobModule',[]);

job.factory('jobFactory',['$http','$q',
	
	function($http, $q) {
	
	var jobUrl = 'http://localhost:2222/theBackendProject';
	
	return {
		addJob : addJob,
		joblist : joblist,
		viewJob : viewJob
        
	};
	
	function addJob(job) {
        var deferred = $q.defer();
        console.log('add job method in factory'+job);
        $http.post(jobUrl + '/job/new', job).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add job mehod in factory');
        return deferred.promise;
    }
	
	function joblist() {
        console.log('Inside job list factory now');
       var deferred = $q.defer();
       $http.get(jobUrl + '/job/list')
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of job list mehod in factory');
           return deferred.promise;
   }
	
	//Function for viewing single blog using blog id as a parameter
    function viewJob(id) {
        console.log('Inside factory now job single');
        var deferred = $q.defer();

        $http.get(jobUrl + '/job/' + id)
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
	
	}]);