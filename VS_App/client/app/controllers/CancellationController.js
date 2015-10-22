/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    'use strict';

    angular
            .module('plunker')
            .controller('CancellationController', CancellationController);

    CancellationController.$inject = ['cancelService', '$scope'];

    function CancellationController(cancelService, $scope) {
        var cancelVm = this;
        $scope.Cancellationsucess = null;
        $scope.cancel = function () {
            cancelService.cancelReservation($scope.ConfirmationNumber)
                    .then(function (Cancellationsucess) {
                        if (Cancellationsucess === "sucess") {
                            $scope.Cancellationsucess = "Your reservation has been cancelled";
                        }
                        else {
                            $scope.Cancellationsucess = "Please enter valid Reservation Number";
                        }
                    }, function (error) {
                        console.log(error);
                        $scope.error = "Please enter valid Reservation Number";
                    });
         
        };
    }
})();