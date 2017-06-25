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
		viewJob : viewJob,
		addJobapp : addJobapp,
		jobapplist : jobapplist,
		editJob : editJob
        
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
	
	// fn to edit job
	function editJob(job) {
        var deferred = $q.defer();
        console.log('editJob method in factory'+job);
        $http.post(jobUrl + '/edit/job', job).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of editJob mehod in factory');
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
	
    // function to apply for a job
    function addJobapp(jobapp) {
        var deferred = $q.defer();
        console.log('add job application method in factory');
        $http.post(jobUrl + '/jobapp/new', jobapp).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add job application mehod in factory');
        return deferred.promise;
    }
    
    function jobapplist(jobid1) {
        console.log('Inside job app list factory now');
       var deferred = $q.defer();
       $http.get(jobUrl + '/jobapp/list/' + jobid1)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of job app list mehod in factory');
           return deferred.promise;
   }
    
    /******************************/
	}]);