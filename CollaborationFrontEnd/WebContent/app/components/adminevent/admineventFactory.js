/**
 * 
 */

var evt = angular.module('evtModule',[]);

evt.factory('admineventFactory',['$http','$q',
	
	function($http, $q) {
	
	var evtUrl = 'http://localhost:2222/theBackendProject';
	
	return {
        addEvt : addEvt,
        evtList : evtList,
        viewEvent : viewEvent,
        addEvtPart : addEvtPart,
        evtpartList : evtpartList,
        editEvt : editEvt
        
	};
	
	function addEvt(evt) {
        var deferred = $q.defer();
        console.log('add evt method in factory'+evt);
        $http.post(evtUrl + '/adminevent/new', evt).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add evt mehod in factory');
        return deferred.promise;
    }
	
	// edit event
	function editEvt(evt) {
        var deferred = $q.defer();
        console.log('editEvt method in factory'+evt);
        $http.post(evtUrl + '/edit/event', evt).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add evt mehod in factory');
        return deferred.promise;
    }
	
	
	//event list method
	function evtList() {
        console.log('Inside evtList factory now');
       var deferred = $q.defer();
       $http.get(evtUrl + '/adminevent/list')
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of evt list mehod in factory');
           return deferred.promise;
   }
    
    // single event method
	function viewEvent(id) {
        console.log('Inside evt factory now');
        var deferred = $q.defer();

        $http.get(evtUrl + '/adminevent/' + id)
            .then (
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    deferred.reject(errResponse);
                }
            );
        	console.log('End of view event method');
            return deferred.promise;
            
    }
	
	// adding new event participant
	function addEvtPart(eventpart) {
        var deferred = $q.defer();
        console.log('add eventpart method in factory'+evt);
        $http.post(evtUrl + '/eventpart/new', eventpart).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add eventpart mehod in factory');
        return deferred.promise;
    }
	
	//event participant list method
	function evtpartList(eventid1) {
        console.log('Inside evt part List factory now');
       var deferred = $q.defer();
       $http.get(evtUrl + '/eventpart/list/' + eventid1)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of evt part list mehod in factory');
           return deferred.promise;
   }
	
	
	
	
    /*******************************/
	}]);