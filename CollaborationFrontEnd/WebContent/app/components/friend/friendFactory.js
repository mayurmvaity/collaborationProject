/**
 * 
 *//**
 * 
 */
var friend = angular.module('friendModule',[]);

friend.factory('friendFactory',['$http','$q',
	
	function($http, $q) {
	
	var friendUrl = 'http://localhost:2222/theBackendProject';
	
	return {
		addFriend : addFriend,
		myFriendlist : myFriendlist,
		notMyFriendlist : notMyFriendlist,
		frreqRcvdlist : frreqRcvdlist,
		myAccFriendlist : myAccFriendlist,
		acceptReq : acceptReq,
		rejectReq : rejectReq,
		cancelReq : cancelReq
        
	};
	
	function addFriend(friend) {
        var deferred = $q.defer();
        console.log('add friend method in factory'+friend);
        $http.post(friendUrl + '/friend/new', friend).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add friend mehod in factory');
        return deferred.promise;
    }
	
	// friend req sent
	function myFriendlist(userid) {
        console.log('Inside frnd req sent list factory now');
       var deferred = $q.defer();
       $http.get(friendUrl + '/friend/myList/' + userid)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of frnd req sent list mehod in factory');
           return deferred.promise;
   }
	
	// not friend list fn
	function notMyFriendlist(userid) {
        console.log('Inside not friend list factory now');
       var deferred = $q.defer();
       $http.get(friendUrl + '/friend/notfrList/' + userid)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of not friend list mehod in factory');
           return deferred.promise;
   }
	
	// friend req recieved
	function frreqRcvdlist(userid) {
        console.log('Inside friend req rcvd list factory now');
       var deferred = $q.defer();
       $http.get(friendUrl + '/friend/req/rcvd/' + userid)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of friend req rcvd list mehod in factory');
           return deferred.promise;
   }
	
	// my frnds list
	function myAccFriendlist(userid) {
        console.log('Inside my frnds list factory now');
       var deferred = $q.defer();
       $http.get(friendUrl + '/friend/frndslist/' + userid)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of my frnds list mehod in factory');
           return deferred.promise;
   }
	
	// function to accept req
    function acceptReq(userid1, userid2) {
        var deferred = $q.defer();
        console.log('Accept method start');
        $http.post(friendUrl + '/frnd/req/accept/' + userid1 + '/' + userid2).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        console.log('Accept method end');
        return deferred.promise;
    }
    
    // function to reject req
    function rejectReq(userid1, userid2) {
        var deferred = $q.defer();
        console.log('Reject req method start');
        $http.post(friendUrl + '/frnd/req/reject/' + userid1 + '/' + userid2).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        console.log('Reject req method end');
        return deferred.promise;
    }
    
    
    // function to cancel req
    function cancelReq(userid1, userid2) {
        var deferred = $q.defer();
        console.log('Cancel req method start');
        $http.post(friendUrl + '/frnd/req/cancel/' + userid1 + '/' + userid2).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        console.log('Cancel req method end');
        return deferred.promise;
    }
	/*************************/
	}]);