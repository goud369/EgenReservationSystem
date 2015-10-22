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

(function() {
  'use strict';

  angular
    .module('plunker')
    .controller('ReservationDetailController',ReservationDetailController);
    
    ReservationDetailController.$inject = ['reservationService'];
    
    function ReservationDetailController(reservationService) {
      var reserVm = this;
      init();
      
      function init(){
          reservationService.getReservations()
           .then(function (reservationDetails) {
                reserVm.reservationDetails=reservationDetails;
          }, function (error) {
            console.log(error);
          }); 
      }
  
    }
})();