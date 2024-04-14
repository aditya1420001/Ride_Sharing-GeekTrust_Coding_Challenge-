package com.geektrust.backend.appConfig;

import com.geektrust.backend.command.CommandInvoker;
import com.geektrust.backend.command.ICommand;
import com.geektrust.backend.command.impl.AddDriverCommand;
import com.geektrust.backend.command.impl.AddRiderCommand;
import com.geektrust.backend.command.impl.BillCommand;
import com.geektrust.backend.command.impl.MatchCommand;
import com.geektrust.backend.command.impl.StartRideCommand;
import com.geektrust.backend.command.impl.StopRideCommand;
import com.geektrust.backend.repository.IDriverRepository;
import com.geektrust.backend.repository.IRideRepository;
import com.geektrust.backend.repository.IRiderRepository;
import com.geektrust.backend.repository.impl.DriverRepositoryImpl;
import com.geektrust.backend.repository.impl.RideRepositoryImpl;
import com.geektrust.backend.repository.impl.RiderRepositoryImpl;
import com.geektrust.backend.service.IDriverService;
import com.geektrust.backend.service.IRideService;
import com.geektrust.backend.service.IRiderService;
import com.geektrust.backend.service.impl.DriverServiceImpl;
import com.geektrust.backend.service.impl.RideServiceImpl;
import com.geektrust.backend.service.impl.RiderServiceImpl;

public class ApplicationConfig {

    // repositories
    private final IRiderRepository riderRepository = new RiderRepositoryImpl();
    private final IDriverRepository driverRepository = new DriverRepositoryImpl();
    private final IRideRepository rideRepository = new RideRepositoryImpl();


    // services
    private final IRiderService riderService = new RiderServiceImpl(riderRepository, driverRepository);
    private final IDriverService driverService = new DriverServiceImpl(driverRepository);
    private final IRideService rideService = new RideServiceImpl(driverRepository, rideRepository, riderService, driverService);


    private final ICommand driverCommand = new AddDriverCommand(driverService);
    private final ICommand riderCommand = new AddRiderCommand(riderService);
    private final ICommand matchCommand = new MatchCommand(riderService);
    private final ICommand startCommand = new StartRideCommand(rideService);
    private final ICommand stopCommand = new StopRideCommand(rideService);
    private final ICommand billCommand = new BillCommand(rideService);



    private final CommandInvoker commandInvoker = new CommandInvoker();
    public CommandInvoker getCommandInvoker() {

        commandInvoker.registerCommand("ADD_DRIVER", driverCommand);
        commandInvoker.registerCommand("ADD_RIDER", riderCommand);
        commandInvoker.registerCommand("MATCH", matchCommand);
        commandInvoker.registerCommand("START_RIDE", startCommand);
        commandInvoker.registerCommand("STOP_RIDE", stopCommand);
        commandInvoker.registerCommand("BILL", billCommand);

        return commandInvoker;
    }

}
