/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    'use strict';

    angular
            .module('plunker')
            .controller('ReservationController', ReservationController);

    ReservationController.$inject = ['reservationService', '$scope', '$rootScope', '$location'];

    function ReservationController(reservationService, $scope, $rootScope, $location) {
        var reservationVm = this;
        reservationVm.rdate = new Date();
        $rootScope.OwnerLoggedIn = false;
        reservationVm.reservedDate = function () {
            var d = new Date($scope.reserveDate);
            var k = d.getFullYear() + "-" + d.getMonth() + "-" + d.getDate();
            return k;
        };
        reservationVm.reservedTime = function () {
            var d = new Date($scope.reserveTime);
            var k = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
            return k;
        };
        reservationVm.timeCheck = function () {
            var d = new Date($scope.reserveTime);
            if (22>=d.getHours() && d.getHours()>=11){
                
                return true;
            }
            else{
                console.log(d.getHours());
                return false;
            }
        };

        reservationVm.reserveTable = function () {
        
            if (reservationVm.timeCheck()) {
                $rootScope.reserDate = reservationVm.reservedDate();
                $rootScope.reserTime = reservationVm.reservedTime();
                $rootScope.Capactity = $scope.NumberOfGuests;
                console.log(reservationVm.reservedDate()+"rootscope timing"+$rootScope.reserDate);
                reservationService.checkAvailabilityOfTable(reservationVm.reservedDate(), reservationVm.reservedTime(), $scope.NumberOfGuests).
                        then(function (tabelCheck) {
                            reservationVm.tabelCheck = tabelCheck;
                            reservationService.SaveReservationData().setReservationData(tabelCheck);
                        }, function (error) {
                            alert(error);
                        });
                reservationService.createCustomer($scope.FullName, $scope.email, $scope.Phone)
                        .then(function (cutomerId) {
                            $rootScope.cutomerId = cutomerId.id;
                            $rootScope.confirmationStatus = true;
                            $location.path('/confirmation');
                        }, function (error) {
                            alert(error);
                        });
            }
            else {
                alert("Restaurant is open All Days  between 11AM to 10PM only Please select correct time between 11AM to 10PM");
                reservationVm.TimeError="Pls select between 11AM to 10PM";
            }
        };
    }
})();




    