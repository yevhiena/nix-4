package ua.com.alevel.controller;

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

        try {
            List<AccountDto> accountDtos = operationService.getAccounts(email);
            System.out.println("Choose account from:");
            for (AccountDto ac :
                    accountDtos) {
                System.out.println("Account number: " + ac.getAccountNumber());
            }
        } catch (SQLException e) {
            throw new Exception("Account does not exist");
        }

        System.out.println("\nEnter account number");
        long number = Long.parseLong(bufferedReader.readLine());
        System.out.println("Enter amount of transfer");
        BigDecimal amount = new BigDecimal(bufferedReader.readLine());
        System.out.println("Enter category");

        List<String> cat = new ArrayList<>();
        System.out.println("Input amount of categories: ");
        int amountOfCat = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < amountOfCat; i++) {
            System.out.println("Write next category: ");
            String category = bufferedReader.readLine();
            cat.add(category);
        }
        OperationDto operationDto = new OperationDto(number, amount, cat);
        TransferResultDto transferResultDto = operationService.makeTransfer(operationDto);
        System.out.println("Operation: " + transferResultDto.getStatus());
        if(transferResultDto.getStatus().equals(TransferResult.Status.REJECTED)){
            System.out.println("Reason " + transferResultDto.getReason());
        }

    }

    private void exportOperationsInCSV() throws Exception {
        try {
            List<AccountDto> accountDtos = reportService.getAccounts(email);
            System.out.println("Choose account from:");
            for (AccountDto ac :
                    accountDtos) {
                System.out.println("Account number: " + ac.getAccountNumber());
            }
        } catch (SQLException e) {
            throw new Exception("Account does not exist");
        }
        System.out.println("Enter account number ");
        long accountNumber = Long.parseLong(bufferedReader.readLine());
        System.out.println("Input start date in format yyyy-MM-dd");
        String start = bufferedReader.readLine();
        System.out.println("Input end date in format yyyy-MM-dd");
        String end = bufferedReader.readLine();

        try {
            LocalDate localDate = LocalDate.parse(start);
            LocalDateTime localDateTime = localDate.atStartOfDay();
            Instant startDate = localDateTime.toInstant(ZoneOffset.UTC);

            LocalDate localDate1 = LocalDate.parse(end);
            LocalDateTime localDateTime1 = localDate1.atTime(23, 59);
            Instant endDate = localDateTime1.toInstant(ZoneOffset.UTC);

            reportService.generateReport(accountNumber, startDate, endDate);
            System.out.println("Report exported to report.csv");
        }catch (DateTimeParseException e){
            System.out.println("Sorry, invalid input of date, does not match pattern");
        } catch (SQLException e){
            System.out.println("Sorry, Account number not found");
        }

    }
}
