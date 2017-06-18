/**
 * 
 */
chat.controller('chatController', ['chatFactory',
        '$timeout', '$cookies', '$routeParams', '$location', '$route', '$rootScope', function (chatFactory,
                $timeout, $cookies, $routeParams, $location, $route, $rootScope) {

var self = this;

                self.messages = [];
                self.message = "";
                self.chatter = $routeParams.fname + " " + $routeParams.lname;
                //self.max = 140;

                self.addMessage = function () {
                        chatFactory.send($rootScope.user.fname + " " + $rootScope.user.lname + " - " + self.message);
                        self.message = "";
                };

                chatFactory.receive().then(null, null, function (message) {
                        self.messages.push(message);
                });
       
                
                
                
}]);