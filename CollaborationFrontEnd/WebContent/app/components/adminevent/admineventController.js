/**
 * 
 */

evt.controller('admineventController',['admineventFactory','$routeParams', '$location', '$route', '$rootScope', '$q',
	function(admineventFactory,$routeParams, $location, $route, $rootScope, $q) {
	
	var self = this;
	
	
	
	self.evt = {
			
			evtid : null,
			etitle : '',
			edata : '',
			active : 'Y'
	}
	
	
	
	self.eventpart = {
			
			eventpartid : null,
			evt : '',
			user : '',
			active : 'Y'
			
	}
	
	self.eventsCount = [];
	
	// variable to hold the list 
	self.evtList = [];
	
	
	
	//function for adding a new evt
    self.addEvt = function () {
    	
    	self.evt.user = $rootScope.user;
    	self.evt.edate = new Date();
    	
        console.log('in evt controller');
         //calling the addBlog method in the factory
        admineventFactory.addEvt(self.evt)
            .then (
                function(evt) {
                    self.evt =  evt;
                    Materialize.toast('Event created successfully', 4000);
                    var bId = self.evt.evtid
                    $location.path('/adminevent/' + bId);
                }, function (errResponse) {
                	console.log("ERROr in controller fn");
                }
            );
         console.log('end of evt controller');
    }
	
    // got to edit page
    /*self.gotoEdit = function(evtId) {
    	console.log('gotoEdit method');
    	admineventFactory.viewEvent(evtId)
    	.then(
    			function(evt1){
    				self.evt = evt1;
    				$location.path('/evt/editEvent');
    			},
    			function(evtError){
    				console.log('Error in gotoevtpage');
    			}
    	);
    	
    }*/
    
    
    //edit event
  //function for editing event
    self.editEvt = function () {

        console.log('in editEvt method controller');
        var evtId = self.evt.evtid;
        self.evt.edate = new Date();
        admineventFactory.editEvt(self.evt)
            .then (
                function(evt1) {
                    self.evt =  evt1;
                    
                    $location.path('/adminevent/' + evtId);
                }, function (errResponse) {
                }
            );
         console.log('end of controller editEvt method');
    }
    
    
    // calling event list method
    evtList();
    
    // evt list method
    function evtList() {
    	
    	console.log('inside event list method');
    	admineventFactory.evtList()
           .then (
               function(evts) {   
                   self.evtList = evts;
                   self.eventsCount = self.evtList.length;
                   console.log(self.evtList);
               },
               function(errResponse) {
                   console.log('Failure!');
               }
           );
        console.log('End of evt list method');
   }
    
 // for viewing single event
	self.singleEvent = {};
    
  //function for viewing single event
    self.viewEvent = function() {
    	
    	evtpartList().then(function(partUsers){
                    self.participantUsers = partUsers; //store list of participated users in already defined array
                    for(var eventpartid in self.participantUsers) {
                        if(user.userid == self.participantUsers[eventpartid].user.userid) { 
                            self.hasParticipated = true;  /*If active user is present in the list of participant set the flag as true & store its fetch its request status*/
                                                
                            break;                     
                        }
                    }
                    
                  //Assigning blog id to variable blogId
                    var evtId = $routeParams.id;
                    admineventFactory.viewEvent(evtId)
                        .then (
                            function(evt) {
                                self.singleEvent = evt;
                                // calling event participants list method
                                self.evt = self.singleEvent;
                                evtpartList();
                            },
                            function(errResponse) {
                            }
                        );
                    
                });
    	
    	
    }
  
    //function for adding a new evt
    self.addEvtPart = function (evtpart1) {
    	
    	self.eventpart.user = $rootScope.user;
    	self.eventpart.evt = evtpart1;
    	self.eventpart.epdate = new Date();
    	
        console.log('in add evtpart controller');
         //calling the addBlog method in the factory
        admineventFactory.addEvtPart(self.eventpart)
            .then (
                function(resp) {
                   $route.reload();
                }, function (errResponse) {
                	console.log("ERROr in add evtpart controller fn");
                }
            );
         console.log('end of add evtpart controller');
    }
   
   
    
    // evt participants list method
    function evtpartList() {
    	var deferred = $q.defer();
    	var eventid1 = $routeParams.id;
    	console.log('inside event participants list method');
    	admineventFactory.evtpartList(eventid1)
           .then (
               function(evtparts) {   
                   self.evtpartList = evtparts;
                   deferred.resolve(evtparts);
                   console.log(self.evtpartList);
               },
               function(errResponse) {
                   console.log('Failure!');
               }
           );
    	
    	return deferred.promise;
        console.log('End of evt participants list method');
   }
    
    // fn to delete an event
    self.deleteAnEvent = function() {
    	
    	var eventid = $routeParams.id;
    	console.log('inside event delete method');
    	admineventFactory.deleteAnEvent(eventid)
           .then (
               function(event) {   
            	 
            	   $location.path('/adminevent/list');
               },
               function(errResponse) {
                   console.log('event delete Failure!');
               }
           );
    	
        console.log('End of event delete method');
   }
    
    // call to eventsParticipated method
    eventsParticipated();
    
    // fn to get list of events participated
    function eventsParticipated() {
    	var userid1 = $rootScope.user.userid;
    	console.log('inside eventsParticipated list method');
    	admineventFactory.eventsParticipated(userid1)
           .then (
               function(eplist) {   
                   self.eventsParticipatedList = eplist;
                   self.eventsParticipatedCount = self.eventsParticipatedList.length;
                   console.log(self.eventsParticipatedList);
               },
               function(errResponse) {
                   console.log('eventsParticipated Failure!');
               }
           );
        console.log('End of eventsParticipated list method');
   }
    
    /************************/
}]);