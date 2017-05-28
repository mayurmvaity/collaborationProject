/**
 * 
 */

evt.controller('admineventController',['admineventFactory','$routeParams', '$location', '$route', '$rootScope', 
	function(admineventFactory,$routeParams, $location, $route, $rootScope) {
	
	var self = this;
	
	
	
	self.evt = {
			
			evtid : null,
			etitle : '',
			edata : '',
			active : 'Y'
	}
	
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
                    var bId = self.evt.evtid
                    $location.path('/adminevent/' + bId);
                }, function (errResponse) {
                	console.log("ERROr in controller fn");
                }
            );
         console.log('end of evt controller');
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
        //Assigning blog id to variable blogId
        var evtId = $routeParams.id;
        admineventFactory.viewEvent(evtId)
            .then (
                function(evt) {
                    self.singleEvent = evt;
                   
                },
                function(errResponse) {
                }
            );

    }
  
    
   
    
}]);