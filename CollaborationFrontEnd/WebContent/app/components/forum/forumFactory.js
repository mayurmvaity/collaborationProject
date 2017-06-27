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
		joinforum : joinforum,
		getParticipatedUsers : getParticipatedUsers,
		nafmemberlist : nafmemberlist,
		fmemberAppr : fmemberAppr,
		joinedforumlist : joinedforumlist,
		createdforumlist : createdforumlist,
		fmemberDisAppr : fmemberDisAppr,
		deleteAForum : deleteAForum,
		editForum : editForum

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
	
	// fn to edit forum
	function editForum(forum) {
        var deferred = $q.defer();
        console.log('editForum method in factory'+forum);
        $http.post(forumUrl + '/edit/forum', forum).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of editForum mehod in factory');
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
    
	function getParticipatedUsers(forumid) {
		var deferred = $q.defer();
        $http.get(forumUrl + '/fmember/tlist/' + forumid)
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
	
	// list of forums
	function myforumlist(userid) {
        console.log('Inside forum list in factory now');
       var deferred = $q.defer();
       
       $http.get(forumUrl + '/forum/myflist/' + userid)
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
	
	// list of nafmemberlist
	function nafmemberlist() {
        console.log('Inside nafmemberlist in factory now');
       var deferred = $q.defer();
       $http.get(forumUrl + '/fmember/nalist')
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of nafmemberlist mehod in factory');
           return deferred.promise;
   }
	
	// function to approve user
    function fmemberAppr(fmemberid) {
        var deferred = $q.defer();
        console.log('Approve fmember method start');
        $http.post(forumUrl + '/fmember/approv/' + fmemberid).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        console.log('Approve fmember method end');
        return deferred.promise;
    }
    
 // function to disapprove user
    function fmemberDisAppr(fmemberid) {
        var deferred = $q.defer();
        console.log('Disapprove fmember method start');
        $http.post(forumUrl + '/fmember/disapprov/' + fmemberid).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        console.log('Disapprove fmember method end');
        return deferred.promise;
    }
	
 // list of joined forums by userid
	function joinedforumlist(userid) {
        console.log('Inside joined forum list method in factory now');
       var deferred = $q.defer();
       $http.get(forumUrl + '/fmember/mylist/' + userid)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of joined forum list method in factory');
           return deferred.promise;
   }
    
	// list of created forums
	function createdforumlist(userid) {
        console.log('Inside list of created forums in factory now');
       var deferred = $q.defer();
       $http.get(forumUrl + '/forum/mylist/' + userid)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of list of created forums mehod in factory');
           return deferred.promise;
   }
	
	 // fn to delete a forum
    function deleteAForum(forumid) {
        console.log('Inside forum delete method factory now');
       var deferred = $q.defer();
       $http.post(forumUrl + '/forum/delete/' + forumid)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of forum delete mehod in factory');
           return deferred.promise;
   }
	
	
    /*********************/
	}]);