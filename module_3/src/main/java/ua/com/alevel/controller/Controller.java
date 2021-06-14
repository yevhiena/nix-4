package ua.com.alevel.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.exception.DataNotFoundException;
import ua.com.alevel.model.dto.AccountDto;
import ua.com.alevel.model.dto.OperationDto;
import ua.com.alevel.model.dto.TransferResultDto;
import ua.com.alevel.model.entity.TransferResult;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.service.ReportService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private BufferedReader bufferedReader;
    private final String email;

    private final OperationService operationService;
    private final ReportService reportService;

    public Controller(String email, OperationService operationService, ReportService reportService) {
        this.email = email;
        this.operationService = operationService;
        this.reportService = reportService;
    }


    public void run() throws Exception {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello!");
        System.out.println("Select you event by entering a number");
        System.out.println("0 - exit");
        System.out.println("1 - perform an operation");
        System.out.println("2 - export operations in CSV file");



        String position;
        while ((position = bufferedReader.readLine()) != null) {
            switch (position) {
                case "0" -> {
                    System.exit(0);
                }
                case "1" -> performOperation();
                case "2" -> exportOperationsInCSV();
                default -> System.out.println("Invalid operation, try again");
            }
            System.out.println("Your variant: if you want exit, please input 0, else, repeat logic");
        }
        bufferedReader.close();
    }


    private void performOperation() throws Exception {
        log.info("Start performing operation");
        try {
            List<AccountDto> accountDtos = operationService.getAccounts(email);
            System.out.println("Choose account from:");
            for (AccountDto ac :
                    accountDtos) {
                System.out.println("Account number: " + ac.getAccountNumber());
            }
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        }

        try {
            System.out.println("\nEnter account number");
            long number = Long.parseLong(bufferedReader.readLine());
            log.info("Entering an account number: " + number);
            System.out.println("Enter amount of transfer");
            BigDecimal amount = new BigDecimal(bufferedReader.readLine());
            log.info("Entering an amount of transfer: " + amount);
            System.out.println("Enter category");

            List<String> cat = new ArrayList<>();
            System.out.println("Input amount of categories: ");
            int amountOfCat = Integer.parseInt(bufferedReader.readLine());
            log.info("Entering an amount of categories: " + amountOfCat);
            for (int i = 0; i < amountOfCat; i++) {
                System.out.println("Write next category: ");
                String category = bufferedReader.readLine();
                log.info("Entering category: " + category);
                cat.add(category);
            }
            log.info("Starting operation");
            OperationDto operationDto = new OperationDto(number, amount, cat);
            TransferResultDto transferResultDto = operationService.makeTransfer(operationDto);
            log.info("Result of operation: " +  transferResultDto.getStatus());
            System.out.println("Operation: " + transferResultDto.getStatus());
            if(transferResultDto.getStatus().equals(TransferResult.Status.REJECTED)){
                System.out.println("Reason " + transferResultDto.getReason());
                log.info("Reason of rejection: " +  transferResultDto.getReason());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for number");
            log.error("Invalid input", e);
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
            log.error("Data layer error", e);
            throw new RuntimeException();
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e);
        }


    }

    private void exportOperationsInCSV() throws Exception {
        log.info("Start performing report export in csv ");
        try {
            List<AccountDto> accountDtos = reportService.getAccounts(email);
            System.out.println("Choose account from:");
            for (AccountDto ac :
                    accountDtos) {
                System.out.println("Account number: " + ac.getAccountNumber());
            }
        } catch (DataNotFoundException e) {
            log.error(e.getMessage(), e);
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }

        try {
            System.out.println("Enter account number ");
            long accountNumber = Long.parseLong(bufferedReader.readLine());
            log.info("Entering account number: "+ accountNumber);
            System.out.println("Input start date in format yyyy-MM-dd");
            String start = bufferedReader.readLine();
            log.info("Entering start date: "+ start);
            System.out.println("Input end date in format yyyy-MM-dd");
            String end = bufferedReader.readLine();
            log.info("Entering end date: "+ end);

            LocalDate localDate = LocalDate.parse(start);
            LocalDateTime localDateTime = localDate.atStartOfDay();
            Instant startDate = localDateTime.toInstant(ZoneOffset.UTC);

            LocalDate localDate1 = LocalDate.parse(end);
            LocalDateTime localDateTime1 = localDate1.atTime(23, 59);
            Instant endDate = localDateTime1.toInstant(ZoneOffset.UTC);
            log.info("Exporting report to csv..");
            reportService.generateReport(accountNumber, startDate, endDate);
            System.out.println("Report exported to report.csv");
            log.info("Report has been successfully exported");
        }catch (DateTimeParseException e){
            System.out.println("Sorry, invalid input of date, does not match pattern");
            log.error("Invalid input of date", e);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            log.error("Data layer error", e);
            throw new RuntimeException();
        }catch (NumberFormatException e){
            System.out.println("Invalid input of number");
            log.error("Invalid input of number", e);
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e);
        }

    }
}
