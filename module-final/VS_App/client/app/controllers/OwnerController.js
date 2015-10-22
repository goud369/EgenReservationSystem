/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    'use strict';

    angular
            .module('plunker')
            .controller('OwnerController', OwnerController)
    // .controller('ReservationDetailController', ReservationDetailController);

    OwnerController.$inject = ['ownerService', '$rootScope', 'reservationService', '$location', '$scope','cancelService','$route'];


    function OwnerController(ownerService, $rootScope, reservationService, $location, $scope,cancelService,$route) {
        var adminVm = this;
        //var reserVm;
        var id = $rootScope.id;
        init();
        $rootScope.logout = function () {
            $rootScope.OwLoggedIn = false;
            $location.path('/login');
        };
         $rootScope.stateCheck = function () {
             if($rootScope.OwLoggedIn===true)
             {  
                $rootScope.OwLoggedIn=true;
                $location.path('/home');
             }
           
                $location.path('/home'); 
             
        };
        $scope.cancellationById=function(cid){
            cancelService.cancelReservation(cid);
            alert("Successfully Cancelled");
            $route.reload();
            reservationService.getReservations()
                    .then(function (reservationDetails) {
                        adminVm.reservationDetails = reservationDetails;
                    }, function (error) {
                        console.log(error);
                    });
            
        };
        function init() {
            ownerService.getOwnerDetails($rootScope.id)
                    .then(function (OwnerDetails) {
                        adminVm.OwnerDetails = OwnerDetails;
                    }, function (error) {
                        console.log(error);
                    });
            reservationService.getReservations()
                    .then(function (reservationDetails) {
                        adminVm.reservationDetails = reservationDetails;
                    }, function (error) {
                        console.log(error);
                    });
        }

    }

})();