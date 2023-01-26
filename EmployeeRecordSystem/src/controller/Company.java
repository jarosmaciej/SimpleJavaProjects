package controller;

import model.Employee;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.zip.*;

public class Company {
    final private LinkedHashMap<String, Employee> list;
    public HashMap<String, Employee> getList() { return list; }
    public Company(){
        list = new LinkedHashMap<>();
    }
    public void addEmployee(Employee e){
        list.put(e.getPesel(), e);
    }
    public void deleteEmployee(String pesel){
        list.remove(pesel);
    }

    public void save(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Input folder name: ");
        String name = scan.nextLine();
        System.out.println("Input compression format (zip, gz): ");
        String format = scan.nextLine();
        if (format.equals("gz")) {
            saveToGZIP(name);
        } else if (format.equals("zip")) {
            saveToZip(name);
        } else {
            System.err.println("Wrong format");
        }
    }
    public void saveToGZIP(String fileName){
        ExecutorService executor = Executors.newFixedThreadPool(10);
        @SuppressWarnings("unchecked")
        CompletableFuture<Void>[] futures = new CompletableFuture[list.size()];
        File dir = new File(System.getProperty("user.dir") + "/" + fileName);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                System.err.println("Directory cannot be created");
                return;
            }
        } else {
            clearDirectory(dir);
        }
        int i = 0;
        for (HashMap.Entry<String, Employee> entry : list.entrySet()) {
            Employee employee = entry.getValue();
            futures[i++] = CompletableFuture.runAsync(() -> saveToGZIPFile(employee, dir.getPath()), executor);
        }
        for (CompletableFuture<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        shutdownAndAwaitTermination(executor);
    }
    public void saveToGZIPFile(Employee employee, String dir) {
        try (FileOutputStream fos = new FileOutputStream(dir + "/" + employee.getPesel() + ".gz");
             GZIPOutputStream gz = new GZIPOutputStream(fos); ObjectOutputStream oos = new ObjectOutputStream(gz)){
            oos.writeObject(employee);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void saveToZip(String fileName) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        @SuppressWarnings("unchecked")
        CompletableFuture<Void>[] futures = new CompletableFuture[list.size()];
        File dir = new File(System.getProperty("user.dir") + "/" + fileName);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                System.err.println("Directory cannot be created");
                return;
            }
        } else {
            clearDirectory(dir);
        }
        int i = 0;
        for (HashMap.Entry<String, Employee> entry : list.entrySet()) {
            Employee employee = entry.getValue();
            futures[i++] = CompletableFuture.runAsync(() -> saveToZipFile(employee, dir.getPath()), executor);
        }
        for (CompletableFuture<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        shutdownAndAwaitTermination(executor);
    }

    private void saveToZipFile(Employee employee, String dir) {
        try (FileOutputStream fos = new FileOutputStream(dir + "/" + employee.getPesel() + ".zip");
             ZipOutputStream zos = new ZipOutputStream(fos); ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(employee);
            byte[] data = baos.toByteArray();
            ZipEntry zipEntry = new ZipEntry(employee.getPesel());
            zos.putNextEntry(zipEntry);
            zos.write(data, 0, data.length);
            zos.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Input folder name: ");
        String name = scan.nextLine();
        System.out.print("Input compression format (zip, gz): ");
        String format = scan.nextLine();
        if (format.equals("gz")) {
            loadGZIP(name);
        } else if (format.equals("zip")) {
            loadZip(name);
        } else {
            System.err.println("Wrong format");
        }
    }
    public void loadZip(String name) {
        File dir = new File(System.getProperty("user.dir") + "/" + name);
        if (!dir.exists()){
            System.err.println("Directory does not exist");
            return;
        }
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Employee>> futures = new ArrayList<>();
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (!file.isDirectory()) {
                if (getExtension(file.getName()).equals("zip")){
                    CompletableFuture<Employee> future = CompletableFuture.supplyAsync(() -> loadZipEmployee(file), executor);
                    futures.add(future);
                }
            }
        }
        list.clear();
        for (CompletableFuture<Employee> future : futures) {
            try {
                Employee employee = future.get();
                list.put(employee.getPesel(), employee);
            } catch (InterruptedException | ExecutionException |
                     CancellationException e) {
                e.printStackTrace();
            }
        }
        shutdownAndAwaitTermination(executor);
    }
    public Employee loadZipEmployee(File file) {
        Employee o = null;
        try (ZipFile zipFile = new ZipFile(file))
        {
            ZipEntry entry =  zipFile.entries().nextElement();
            ObjectInputStream oos = new ObjectInputStream(zipFile.getInputStream(entry));
            o = (Employee) oos.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return o;
    }
    public void loadGZIP(String name){
        File dir = new File(System.getProperty("user.dir") + "/" + name);
        if (!dir.exists()){
            System.err.println("Directory does not exist");
            return;
        }
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Employee>> futures = new ArrayList<>();
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (!file.isDirectory()) {
                if (getExtension(file.getName()).equals("gz")){
                    CompletableFuture<Employee> future = CompletableFuture.supplyAsync(() -> loadGZIPEmployee(file), executor);
                    futures.add(future);
                }
            }
        }
        list.clear();
        for (CompletableFuture<Employee> future : futures) {
            try {
                Employee employee = future.get();
                list.put(employee.getPesel(), employee);
            } catch (InterruptedException | ExecutionException |
                     CancellationException e) {
                e.printStackTrace();
            }
        }
        shutdownAndAwaitTermination(executor);
    }
    public Employee loadGZIPEmployee(File file) {
        Employee o = null;
        try(GZIPInputStream gis = new GZIPInputStream(new FileInputStream(file));
            ObjectInputStream in = new ObjectInputStream(gis)){
            o = (Employee) in.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return o;
    }
    void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    public String getExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i+1);
        }
        else return null;
    }
    public void clearDirectory(File dir){
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory())
                clearDirectory(file);
            if (!file.delete())
                System.err.println("File " + file.getName() + " couldn't be deleted");
        }
    }
}