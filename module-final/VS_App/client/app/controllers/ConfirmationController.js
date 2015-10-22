/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    'use strict';

    angular
            .module('plunker')
            .controller('ConfirmationController', ConfirmationController);

    ConfirmationController.$inject = ['reservationService', '$rootScope', '$scope'];

    function ConfirmationController(reservationService, $rootScope, $scope) {
        var confirmVm = this;
        reservationService.SaveReservationData().getReservationData();
        confirmVm.reserveStatus = reservationService.SaveReservationData().getReservationData().status;
        confirmVm.table_id = reservationService.SaveReservationData().getReservationData().table_id;

        init();
        function init() {
            reservationService.reserveTable($rootScope.Capactity, $rootScope.reserDate, $rootScope.reserTime, $rootScope.cutomerId, confirmVm.table_id, confirmVm.reserveStatus)
                    .then(function (reservstionInvoiceDeatils) {
                        confirmVm.reservstionInvoiceDeatils = reservstionInvoiceDeatils;
                        $scope.resv = confirmVm.reservstionInvoiceDeatils;
                    }, function (error) {
                        alert(error);
                    });
        }


    }
})();