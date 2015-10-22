/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    'use strict';

    angular
            .module('plunker')
            .service('reservationService', reservationService);

    reservationService.$inject = ['$q', '$http'];

    function reservationService($q, $http) {
        var self = this;

        self.getReservations = getReservations;
        self.createCustomer=createCustomer;
        self.checkAvailabilityOfTable=checkAvailabilityOfTable;
        self. reserveTable= reserveTable;
        // self.getReservationsById = getReservationsById;
        
         var reservationService = this;
        var saveReservationData = {};
        reservationService.SaveReservationData=function(){
            function setReservationData(reservedata) {
            saveReservationData = reservedata;
        }
        function getReservationData() {
            return saveReservationData;
        }

        return {
            setReservationData: setReservationData,
            getReservationData: getReservationData
        };
    };
    
        function getReservations() {
            var defer = $q.defer();
            $http.get('http://localhost:8080/vs/reservations/')
                    .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);

            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;

        }
        ;
        function createCustomer(Name, email, phone) {
            var defer = $q.defer();

            $http.post('http://localhost:8080/vs/customers', {"name": Name, "email": email, "phone": phone})
                    .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;

        };
        function checkAvailabilityOfTable(reserveDate, reserveTime,Capacity) {
            var defer = $q.defer();

            $http.get('http://localhost:8080/vs/tablecheck/'+reserveDate+'/'+reserveTime+'/'+Capacity)
                    .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);
              
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;
        };
          function reserveTable(numberOfGuests, reserveDate, reserveTime, cutomer_id, table_id, restatus) {
            var defer = $q.defer();
            var reservation={"numberOfGuests":numberOfGuests,
                             "reservation_Date":reserveDate,
                             "reservation_Time":reserveTime, 
                             "customer_id":cutomer_id, 
                             "table_id":table_id, 
                             "reservation_Status":restatus};
                       
            $http.post('http://localhost:8080/vs/reservations',angular.toJson(reservation, true))
                    .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);   
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;
        }
    }
})();