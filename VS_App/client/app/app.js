(function () {
    'use strict';

    angular
            .module('plunker', ['ngRoute','ngCookies'])
            .config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider', '$httpProvider'];

    function moduleConfig($routeProvider, $httpProvider) {

        $routeProvider
                .when('/home', {
                    templateUrl: 'views/home/home.html',
                    controller: 'ReservationController',
                    controllerAs: 'reservationVm'
                })
                .when('/login', {
                    templateUrl: 'views/login.html',
                    controller: 'LoginController',
                    controllerAs: 'loginvm'
                })
                .when('/admin', {
                     resolve: {
                        "check": function ($rootScope, $location) {
                            if (!$rootScope.OwLoggedIn) {
                                $location.path('/login');
                            }
                        }
                    },
                    templateUrl: 'views/OwnersDashBoard/ownersHomePage.html',
                    controller: 'OwnerController',
                    controllerAs: 'adminVm'
                })
                .when('/admin/profile', {
                     resolve: {
                        "check": function ($rootScope, $location) {
                            if (!$rootScope.OwLoggedIn) {
                                $location.path('/login');
                            }
                        }
                    },
                    templateUrl: 'views/OwnersDashBoard/profile.html',
                    controller: 'profileController',
                    controllerAs: 'profileVm'
                })
                .when('/confirmation', {
                    resolve: {
                        "check": function ($rootScope, $location) {
                            if (!$rootScope.confirmationStatus) {
                                $location.path('/home');
                            }
                        }
                    },
                    templateUrl: 'views/confirmation.html',
                    controller: 'ConfirmationController',
                    controllerAs: 'confirmVm'
                })
                .when('/cancellation', {
                    templateUrl: 'views/cancelReservation.html',
                    controller: 'CancellationController',
                    controllerAs: 'cancelVm'
                })

                .otherwise({
                    redirectTo: '/home'
                });

        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }


})();