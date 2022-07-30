package com.witalis.jkit.usage.core.invoke.section.io;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.io.context.*;

import com.witalis.jkit.usage.core.invoke.section.io.context.IO;
import com.witalis.jkit.usage.core.invoke.section.io.context.IOEnum;
import com.witalis.jkit.usage.core.invoke.section.io.context.IOSerial;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Desc: input/output
 * User: Wellaxis
 * Date: 2019/11/18
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class IOInvoker extends Invoker {

    public IOInvoker() {
        setTitle("Input/Output chapter.");
    }

    @Override
    public void invoke() {
        // io
        log.info("-- I/O");
        invokeIO();
        // tab
        log.info("");
        // nio
        log.info("-- New I/O");
        invokeNIO();
    }

    /**
     * I/O functionality
     */
    public void invokeIO() {
        // file
        log.info("---- I/O File");
        invokeIOFile();
        // bytes I/O
        {
            // stream
            log.info("---- I/O Stream");
            invokeIOStream();
            // file stream
            log.info("---- I/O File Stream");
            invokeIOFileStream();
            // byte array stream
            log.info("---- I/O Byte Array Stream");
            invokeIOByteArrayStream();
            // filter stream
            log.info("---- I/O Filter Stream");
            invokeIOFilterStream();
            // buffered stream
            log.info("---- I/O Buffered Stream");
            invokeIOBufferedStream();
            // pushback stream
            log.info("---- I/O Pushback Stream");
            invokeIOPushbackStream();
            // data stream
            log.info("---- I/O Data Stream");
            invokeIODataStream();
            // sequence stream
            log.info("---- I/O Sequence Stream");
            invokeIOSequenceStream();
            // object stream
            log.info("---- I/O Object Stream");
            invokeIOObjectStream();
        }
        // chars R/W
        {
            // stream
            log.info("---- R/W Stream");
            invokeRWStream();
            // file stream
            log.info("---- R/W File Stream");
            invokeRWFileStream();
            // char array stream
            log.info("---- R/W Char Array Stream");
            invokeRWCharArrayStream();
            // buffered stream
            log.info("---- R/W Buffered Stream");
            invokeRWBufferedStream();
            // pushback stream
            log.info("---- R/W Pushback Stream");
            invokeRWPushbackStream();
        }
        // console
        log.info("---- I/O Console");
        invokeIOConsole();
    }

    /**
     * I/O file functionality
     */
    public void invokeIOFile() {
        // File - it deals directly with files and the file system
        // Stream - an abstraction that produces or consumes information

        log.info(" ------ File");
        // file
        log.info(" -- Root");
        File file = new File("/");
        log.info(" * File: " + file.getAbsolutePath());
        var exists = file.exists();
        log.info(" * File exists: " + exists);
        var isFile = file.isFile();
        log.info(" * File is file: " + isFile);
        var isDirectory = file.isDirectory();
        log.info(" * File is directory: " + isDirectory);
        var isAbsolute = file.isAbsolute();
        log.info(" * File is absolute: " + isAbsolute);
        var isHidden = file.isHidden();
        log.info(" * File is hidden: " + isHidden);
        var canRead = file.canRead();
        log.info(" * File can read: " + canRead);
        var canWrite = file.canWrite();
        log.info(" * File can write: " + canWrite);
        var canExecute = file.canExecute();
        log.info(" * File can execute: " + canExecute);
        var size = file.length();
        log.info(" * File length: " + size);
        var lastModified = file.lastModified();
        log.info(" * File last modified: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(lastModified), ZoneId.systemDefault()));
        // list
        log.info(" -- Temp");
        File temp = new File(file, "temp");
        log.info(" * File name: " + temp.getName());
        log.info(" * File path: " + temp.getPath());
        log.info(" * File parent: " + temp.getParent());
        var list = temp.list();
        if (list != null)
            log.info(" * All files: " + list.length);
        // filter
        var gradles = temp.listFiles((dir, name) -> name.endsWith(".gradle"));
        if (gradles != null)
            log.info(" * Only gradle files: " + gradles.length);
        // action
        log.info(" -- Action");
        try {
            File dir = new File(temp, "tmp");
            log.info(" * Directory: " + dir.getAbsolutePath());
            log.info(" * Directory make: " + dir.mkdirs());
            File tmp = new File(dir, "tmp.txt");
            log.info(" * File: " + tmp.getAbsolutePath());
            // process
            var created = tmp.createNewFile();
            if (created && tmp.exists()) {
                log.info(" * File info: " + tmp.toURI());
                var tmpMd = new File(temp, "tmp.md");
                var renamed = tmp.renameTo(tmpMd);
                if (renamed) {
                    tmp = tmpMd;
                    log.info(" * File info: " + tmp.toURI());
                }
                var readOnly = tmp.setReadOnly();
                log.info(" * File read only: " + readOnly);
                var deleted = tmp.delete();
                log.info(" * File delete: " + deleted);
            }
            dir.deleteOnExit();
        } catch (IOException e) {
            log.error("Unable to process file: " + e.getLocalizedMessage());
        }
    }

    /**
     * I/O stream functionality
     * input/output stream - bytes
     */
    public void invokeIOStream() {
        // IS - is an abstract class that defines streaming byte input
        // OS - is an abstract class that defines streaming byte output
        // PS - provides all of the output capabilities from the System.out

        log.info(" ------ Input/Output Stream");
        // byte streams
        InputStream is = null;
        OutputStream os = null;
        // system streams
        InputStream sis = System.in;
        OutputStream sos = System.out;
        PrintStream ps = System.err;
        // symbol
        IO io = new IO(IO.MD_SYMBOL, true, sis, sos);
        try {
            io.read();
            var i = 14;
            while (i > 0) {
                io.write('-');
                i--;
            }
            io.write('>');
            io.write(' ');
            io.write('I');
            io.write('/');
            io.write('O');
            io.write(':');
            io.write('\n');
        } catch (IOException e) {
            ps.println(e.getMessage());
        }
        // line
        io = new IO(IO.MD_LINE, true, sis, sos);
        try {
            io.read();
            io.print("print 'It'.");
            io.print("print 'is'.");
            io.print("print 'Ok'.");
        } catch (IOException e) {
            ps.println(e.getMessage());
        }
    }

    /**
     * I/O file stream functionality
     * file i/o stream
     */
    public void invokeIOFileStream() {
        // FIS - creates an InputStream to read bytes from a file
        // FOS - creates an OutputStream to write bytes to a file

        log.info(" ------ File I/O Stream");
        // file
        URL sourceUrl = getClass().getClassLoader().getResource("file/source.txt");
        assert sourceUrl != null : "Source file has not been found!";
        File source = new File(sourceUrl.getFile());
        log.info("    Source file: " + source.getAbsolutePath());
        URL targetUrl = getClass().getClassLoader().getResource("file/target.txt");
        assert targetUrl != null : "Target file has not been found!";
        File target = new File(targetUrl.getFile());
        log.info("    Target file: " + target.getAbsolutePath());
        // original file processing
        FileInputStream fis = null;
        FileOutputStream fos = null;
        int i;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            do {
                i = fis.read();
                if (i != -1) {
                    fos.write((char) i);
                }
            } while (i != -1);
        } catch (FileNotFoundException fnfe) {
            log.error("File not found errors: " + fnfe.getLocalizedMessage());
        } catch (IOException ioe) {
            log.error("Input/output errors: " + ioe.getLocalizedMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // ARM - automatic resource management, since JDK 7 (try-with-resources)
        // AutoCloseable interface
        // all statements in try (...) - are final
        try (var fin = new FileInputStream(source);
             final var fout = new FileOutputStream(target)
        ) {
            var size = fin.available();
            log.info(" * Source size: " + size);
            fin.skip(size / 2 - 1);
            log.info(" * After skip size: " + fin.available());
            do {
                if ((i = fin.read()) != -1)
                    fout.write((char) i);
            } while (i != -1);
        } catch (IOException ioe) {
            log.error("Input/output [file] errors: " + ioe.getLocalizedMessage());
        }
    }

    /**
     * I/O byte array stream functionality
     * byte array i/o stream
     */
    public void invokeIOByteArrayStream() {
        // BAIS - is an implementation of an input stream that uses a byte array as the source
        // BAOS - is an implementation of an output stream that uses a byte array as the destination

        log.info(" ------ Byte Array I/O Stream");
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        byte[] bytes = alpha.getBytes();
        var part = new ByteArrayInputStream(bytes, 10, bytes.length / 2);
        try (var bais = new ByteArrayInputStream(bytes);
             var baos = new ByteArrayOutputStream();
        ) {
            byte[] buf = new byte[bytes.length];
            int i = 0;
            while (i < 3) {
                StringBuilder builder = new StringBuilder();
                int c;
                while ((c = bais.read()) != -1) {
                    switch (i) {
                        case 0 -> {
                            baos.write(c);
                            builder.append((char) c);
                        }
                        case 1 -> builder.append(Character.toUpperCase((char) c));
                        case 2 -> builder.append(Character.reverseBytes((char) c));
                    }
                }
                log.info(" * ByteArray mode[IN/" + i + "]: " + builder.toString());
                // renew stream to read again
                bais.reset();
                i++;
            }
            log.info(" * ByteArray mode[OUT]: " + baos.toString());
            var compare = Arrays.compare(bytes, baos.toByteArray());
            log.info(" * ByteArray compare: " + compare);
        } catch (IOException ioe) {
            log.error("Input/output [byte array] errors: " + ioe.getLocalizedMessage());
        }
    }

    /**
     * I/O filter stream functionality
     * filter i/o stream
     */
    public void invokeIOFilterStream() {
        // Filtered stream - is simply wrapper around underlying input or output stream
        // Filtered stream - is typically accessed by methods that are expecting a generic stream

        log.info(" ------ Filter I/O Stream");
        FilterInputStream fis = null;
        FilterOutputStream fos = null;
    }

    /**
     * I/O buffered stream functionality
     * buffered i/o stream
     */
    public void invokeIOBufferedStream() {
        // Buffered stream - extends a filtered stream class by attaching a memory buffer to the I/O stream
        // Buffered stream - allows to do I/O operations on more than a byte at a time, thereby improving performance

        log.info(" ------ Buffered I/O Stream");
        var s = "This is a &copy; copyright symbol, but this is &copy not.";
        var bytes = s.getBytes();
        log.info(" * Original value: " + s);
        var builder = new StringBuilder();
        try (var is = new ByteArrayInputStream(bytes);
             var bis = new BufferedInputStream(is);
        ) {
            int c;
            boolean marked = false;
            while ((c = bis.read()) != -1) {
                switch (c) {
                    case '&' -> {
                        if (!marked) {
                            // mark this place
                            bis.mark(32);
                            marked = true;
                        } else {
                            marked = false;
                        }
                    }
                    case ';' -> {
                        if (marked) {
                            marked = false;
                            // done mark - success
                            builder.append('\u00a9');
                        } else {
                            builder.append((char) c);
                        }
                    }
                    case ' ' -> {
                        if (marked) {
                            marked = false;
                            // reset mark - failure
                            bis.reset();
                            builder.append("&");
                        } else {
                            builder.append((char) c);
                        }
                    }
                    default -> {
                        if (!marked)
                            builder.append((char) c);
                    }
                }
            }
            log.info(" * Buffered I/O: " + builder.toString());
        } catch (IOException ioe) {
            log.error("Input/output [buffered] errors: " + ioe.getLocalizedMessage());
        }
        // flush data
        var flashData = false;
        if (flashData) {
            try (var bos = new BufferedOutputStream(System.out)) {
                bos.flush();
            } catch (IOException ioe) {
                log.error("Input/output [buffered] errors: " + ioe.getLocalizedMessage());
            }
        }
    }

    /**
     * I/O push-back stream functionality
     * push-back i/o stream
     */
    public void invokeIOPushbackStream() {
        // Pushback stream - is used on an input stream to allow a byte to be read and then returned to the stream
        // Pushback stream - provides a mechanism to "peek" at what is coming from an input stream without disrupting it

        log.info(" ------ Pushback I/O Stream");
        var s = "if (a == 4) a = 0; \n";
        var bytes = s.getBytes();
        log.info(" * Original value: " + s);
        var builder = new StringBuilder();
        try (var is = new ByteArrayInputStream(bytes);
             var pis = new PushbackInputStream(is)
        ) {
            int c;
            while ((c = pis.read()) != -1) {
                switch (c) {
                    case '=' -> {
                        if ((c = pis.read()) == '=')
                            builder.append("equals to");
                        else {
                            builder.append("set as");
                            // return to stream
                            pis.unread(c);
                        }
                    }
                    default -> builder.append((char) c);
                }
            }
            log.info(" * Pushback I/O: " + builder.toString());
        } catch (IOException ioe) {
            log.error("Input/output [pushback] errors: " + ioe.getLocalizedMessage());
        }
    }

    /**
     * I/O data stream functionality
     * data i/o stream
     */
    public void invokeIODataStream() {
        // DIS - enable to read primitive data from a stream
        // DOS - enable to write primitive data to a stream
        // They implement the DataInput and DataOutput interfaces

        log.info(" ------ Data I/O Stream");

        URL sourceUrl = getClass().getClassLoader().getResource("file/type.txt");
        assert sourceUrl != null : "Source file has not been found!";
        File file = new File(sourceUrl.getFile());
        // write data
        try (var dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeDouble(98.76D);
            dos.writeInt(123);
            dos.writeBoolean(true);
            dos.writeByte(20);
            dos.writeChar('|');
            dos.writeFloat(3.14F);
            dos.writeLong(9999999L);
            dos.writeShort(33);
            dos.writeUTF("UTF");
        } catch (IOException ioe) {
            log.error("Input/output [data] errors: " + ioe.getLocalizedMessage());
        }
        // read data
        try (var dis = new DataInputStream(new FileInputStream(file))) {
            double d = dis.readDouble();
            int i = dis.readInt();
            boolean b = dis.readBoolean();
            byte bb = dis.readByte();
            char c = dis.readChar();
            float f = dis.readFloat();
            long l = dis.readLong();
            short s = dis.readShort();
            String utf = dis.readUTF();
            // log
            log.info(" * double: " + d);
            log.info(" * int: " + i);
            log.info(" * boolean: " + b);
            log.info(" * byte: " + bb);
            log.info(" * char: " + c);
            log.info(" * float: " + f);
            log.info(" * long: " + l);
            log.info(" * short: " + s);
            log.info(" * string: " + utf);
        } catch (IOException ioe) {
            log.error("Input/output [data] errors: " + ioe.getLocalizedMessage());
        }
    }

    /**
     * I/O sequence stream functionality
     * sequence i/o stream
     */
    public void invokeIOSequenceStream() {
        // SequenceInputStream - allows you to concatenate multiple InputStreams

        log.info(" ------ Sequence I/O Stream");
        var data = new Vector<String>();
        data.addElement("Hello, ");
        data.addElement("it's the ");
        data.addElement("java's ");
        data.addElement("world. ");
        var ise = new IOEnum(data);
        try (var is = new SequenceInputStream(ise)) {
            int c;
            var builder = new StringBuilder();
            while ((c = is.read()) != -1) {
                builder.append((char) c);
            }
            log.info(" * Sequence I/O: " + builder.toString());
        } catch (IOException ioe) {
            log.error("Input/output [sequence] errors: " + ioe.getLocalizedMessage());
        }
    }

    /**
     * I/O object stream functionality
     * object i/o stream - serialization
     */
    public void invokeIOObjectStream() {
        // OIS - responsible for reading objects from a stream - from which serialized objects should be read
        // OOS - responsible for writing objects to a stream - to which serialized objects will be written

        log.info(" ------ Object I/O Stream");
        URL fileUrl = getClass().getClassLoader().getResource("file");
        assert fileUrl != null : "Object file has not been found!";
        File file = new File(fileUrl.getFile() + File.separator + "serial.txt");
        // object
        IOSerial iosProducer = null;
        IOSerial iosConsumer = null;
        // serialization
        try (var oos = new ObjectOutputStream(new FileOutputStream(file))) {
            iosProducer = new IOSerial(35, "Bill", true, 26534.78, "X75Y");
            log.info(" * Object I/O producer: " + iosProducer.toString());
            oos.writeObject(iosProducer);
        } catch (IOException ioe) {
            log.error("Input/output [object] errors: " + ioe.getLocalizedMessage());
        }
        // de-serialization
        try (var ois = new ObjectInputStream(new FileInputStream(file))) {
            iosConsumer = (IOSerial) ois.readObject();
            log.info(" * Object I/O consumer: " + iosConsumer.toString());
        } catch (IOException | ClassNotFoundException ioe) {
            log.error("Input/output [object] errors: " + ioe.getLocalizedMessage());
        }
        // comparison
        assert iosProducer != null : "Serial producer should be specified";
        assert iosConsumer != null : "Serial consumer should be specified";
        log.info(" * Object I/O compare: " + iosProducer.equals(iosConsumer));
    }

    /**
     * R/W stream functionality
     * reader/writer stream - chars
     */
    public void invokeRWStream() {
        // Reader - an abstract class that defines streaming character input
        // Writer - an abstract class that defines streaming character output

        log.info(" ------ Reader/Writer");
        // character streams
        Reader r;
        Writer w;
        PrintWriter pw;
    }

    /**
     * R/W file stream functionality
     * file r/w stream
     */
    public void invokeRWFileStream() {
        // FR - creates a Reader to read the contents of a file
        // FW - creates a Writer to write to a file some content

        log.info(" ------ File Reader/Writer");
        // reader
        URL sourceUrl = getClass().getClassLoader().getResource("file/text.txt");
        assert sourceUrl != null : "Source file has not been found!";
        File source = new File(sourceUrl.getFile());
        try (var fr = new FileReader(source, Charset.defaultCharset())) {
            int c;
            var builder = new StringBuilder();
            while ((c = fr.read()) != -1)
                builder.append((char) c);
            log.info(" * File reader content: " + builder.toString());
        } catch (IOException ioe) {
            log.error("Reader/writer [file] errors: " + ioe.getLocalizedMessage());
        }
        // writer
        var info = "Small, Simple, and Decoupled Services = Scalable, Resilient, and Flexible Applications";
        char[] buffer = new char [info.length()];
        // copies characters from string into the destination character array
        info.getChars(0, info.length(), buffer, 0);
        var target = source.getParent() + File.separator + "slogan.txt";
        try (var fw = new FileWriter(target)) {
            fw.write(buffer);
            log.info(" * File writer content: " + info);
        } catch (IOException ioe) {
            log.error("Reader/writer [file] errors: " + ioe.getLocalizedMessage());
        }
    }

    /**
     * R/W char array stream functionality
     * char array r/w stream
     */
    public void invokeRWCharArrayStream() {
        // CAR - an implementation of an input stream that uses a character array as the source
        // CAW - an implementation of an output stream that uses an array as the destination

        log.info(" ------ Char Array Reader/Writer");
        // reader
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char[] chars = new char[alpha.length()];
        alpha.getChars(0, alpha.length(), chars, 0);
        try (var car = new CharArrayReader(chars);
             var par = new CharArrayReader(chars, 10, 10);
             var caw = new CharArrayWriter();
        ) {
            int c;
            // reader
            var builder = new StringBuilder();
            while ((c = car.read()) != -1)
                builder.append((char) c);
            log.info(" * Char Array R: " + builder.toString());
            builder = new StringBuilder();
            while ((c = par.read()) != -1)
                builder.append((char) c);
            log.info(" * Char Array reader part: " + builder.toString());
            // writer
            caw.write(chars);
            builder = new StringBuilder();
            for (char ch : caw.toCharArray()) {
                builder.append(ch);
            }
            log.info(" * Char Array W: " + builder.toString());
        } catch (IOException ioe) {
            log.error("Reader/writer [char array] errors: " + ioe.getLocalizedMessage());
        }
    }

    /**
     * R/W buffered stream functionality
     * buffered r/w stream
     */
    public void invokeRWBufferedStream() {
        // BR - improves performance by buffering input
        // BW - is a writer that buffers output

        log.info(" ------ Buffered Reader/Writer");
        var s = "This is a &copy; copyright symbol, but this is &copy not.";
        var chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);
        log.info(" * Original value: " + s);
        var builder = new StringBuilder();
        try (var cas = new CharArrayReader(chars);
             var br = new BufferedReader(cas);
        ) {
            int c;
            boolean marked = false;
            while ((c = br.read()) != -1) {
                switch (c) {
                    case '&' -> {
                        if (!marked) {
                            // mark this place
                            br.mark(32);
                            marked = true;
                        } else {
                            marked = false;
                        }
                    }
                    case ';' -> {
                        if (marked) {
                            marked = false;
                            // done mark - success
                            builder.append('\u00a9');
                        } else {
                            builder.append((char) c);
                        }
                    }
                    case ' ' -> {
                        if (marked) {
                            marked = false;
                            // reset mark - failure
                            br.reset();
                            builder.append("&");
                        } else {
                            builder.append((char) c);
                        }
                    }
                    default -> {
                        if (!marked)
                            builder.append((char) c);
                    }
                }
            }
            log.info(" * Buffered R/W: " + builder.toString());
        } catch (IOException ioe) {
            log.error("Reader/writer [buffered] errors: " + ioe.getLocalizedMessage());
        }
    }

    /**
     * R/W push-back stream functionality
     * push-back r/w stream
     */
    public void invokeRWPushbackStream() {
        // Pushback stream - allows one or more characters to be returned to the input stream
        // Pushback stream - allows to look ahead in the input stream

        log.info(" ------ Pushback R/W Stream");
        var s = "if (a == 4) a = 0; \n";
        var chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);
        log.info(" * Original value: " + s);
        var builder = new StringBuilder();
        try (var car = new CharArrayReader(chars);
             var pr = new PushbackReader(car)
        ) {
            int c;
            while ((c = pr.read()) != -1) {
                switch (c) {
                    case '=' -> {
                        if ((c = pr.read()) == '=')
                            builder.append("equals to");
                        else {
                            builder.append("set as");
                            // return to stream
                            pr.unread(c);
                        }
                    }
                    default -> builder.append((char) c);
                }
            }
            log.info(" * Pushback R/W: " + builder.toString());
        } catch (IOException ioe) {
            log.error("Reader/writer [pushback] errors: " + ioe.getLocalizedMessage());
        }
    }

    /**
     * I/O console functionality
     */
    public void invokeIOConsole() {
        // Console - is used to read from and write to the console, if one exists
        // Console supplies no constructors - System.console()

        log.info(" ------ Console");
        var console = System.console();
        if (console == null) return;
        console.writer().println(" * Output to system console...");
        var line = console.readLine(" * Input console line: ");
        console.printf(" * You input the following line: %s", line);
    }

    /**
     * New I/O functionality
     */
    public void invokeNIO() {
        // path
        log.info("---- New I/O Path");
        invokeNIOPath();
        // file
        log.info("---- New I/O File");
        invokeNIOFile();
        // file system
        log.info("---- New I/O File System");
        invokeNIOFileSystem();
        // buffer
        log.info("---- New I/O Buffer");
        invokeNIOBuffer();
        // channel
        log.info("---- New I/O Channel");
        invokeNIOChannel();
        // stream
        log.info("---- New I/O Stream");
        invokeNIOStream();
        // selector
        log.info("---- New I/O Selector");
        invokeNIOSelector();
    }

    /**
     * New I/O path functionality
     */
    public void invokeNIOPath() {
        // Path - interface, it encapsulates a path to a file
        // Path - describes a file's location within the directory structure
        // Paths - to create an instance of Path - not recommended

        log.info(" ------ Path");
        File file = new File("/temp/tmp");
        Path path = file.toPath();
        log.info(" * Path info: " + path.toString());
        log.info(" * Path parent: " + path.getParent());
        log.info(" * Path root: " + path.getRoot());
        log.info(" * Path file name: " + path.getFileName());
        log.info(" * Path name: " + path.getName(0));
        log.info(" * Path name count: " + path.getNameCount());
        log.info(" * Path resolve: " + path.resolve(path));
        log.info(" * Path is absolute: " + path.isAbsolute());
        log.info(" * Path to absolute: " + path.toAbsolutePath());
        log.info(" * Path to file: " + path.toFile());
        log.info(" * Path to URI: " + path.toUri());
        // new instance - since JDK 11
        var pathOf = Path.of(file.toURI());
        log.info(" * Path of: " + pathOf.toString());

        // paths - it is no longer recommended, user Path.of instead
        var pathGet = Paths.get(file.toURI());
        log.info(" * Paths get: " + pathGet.toString());
    }

    /**
     * New I/O file functionality
     */
    public void invokeNIOFile() {
        // Files - many of the actions on a file are provided by static methods

        log.info(" ------ File");
        var root = new File("/temp");
        var dir = new File(root.getAbsolutePath() + File.separator + "test");
        var file = new File(dir.getAbsolutePath() + File.separator + "text.txt");
        try {
            // prepare
            Files.deleteIfExists(file.toPath());
            Files.deleteIfExists(dir.toPath());
            // process
            var path = Files.createDirectory(dir.toPath());
            log.info(" * Files create directory: " + path.toString());
            var text = Files.createFile(file.toPath());
            log.info(" * Files create file: " + text.toString());
            log.info(" * Files is directory: " + Files.isDirectory(text));
            log.info(" * Files is regular: " + Files.isRegularFile(text));
            log.info(" * Files is executable: " + Files.isExecutable(text));
            log.info(" * Files is readable: " + Files.isReadable(text));
            log.info(" * Files is writable: " + Files.isWritable(text));
            log.info(" * Files is hidden: " + Files.isHidden(text));
            // attributes
            var attributes = Files.readAttributes(
                text,
                BasicFileAttributes.class,
                LinkOption.NOFOLLOW_LINKS
            );
            log.info(" * Files attributes: " + attributes.toString());
            log.info("   - file key: " + attributes.fileKey());
            log.info("   - is directory: " + attributes.isDirectory());
            log.info("   - is file: " + attributes.isRegularFile());
            log.info("   - is link: " + attributes.isSymbolicLink());
            log.info("   - is other: " + attributes.isOther());
            log.info("   - size: " + attributes.size());
            log.info("   - creation time: " + attributes.creationTime());
            log.info("   - last access time: " + attributes.lastAccessTime());
            log.info("   - last modified time: " + attributes.lastModifiedTime());
            attributes = Files.readAttributes(
                text,
                DosFileAttributes.class,
                LinkOption.NOFOLLOW_LINKS
            );
            log.info(" * Files Dos attributes: " + attributes.toString());
            // action
            var copy = Files.copy(
                text,
                Path.of(root.getAbsolutePath() + "/tested.txt"),
                StandardCopyOption.REPLACE_EXISTING
            );
            log.info(" * Files copy file: " + copy.toString());
            var exists = Files.exists(copy, LinkOption.NOFOLLOW_LINKS);
            log.info(" * Files exists: " + exists);
            Files.delete(copy);
            log.info(" * Files delete: " + true);
            log.info(" * Files not exists: " + Files.notExists(copy));
            var owner = Files.getOwner(text);
            log.info(" * Files owner file: " + owner.toString());
            var fileWriter = new FileWriter(text.toFile());
            fileWriter.write("Temporary testing text...");
            fileWriter.flush();
            fileWriter.close();
            log.info(" * Files size file: " + Files.size(text));
            var inputStream = Files.newInputStream(text, StandardOpenOption.READ);
            var content = new String(inputStream.readAllBytes());
            log.info(" * Files stream content: " + content);
        } catch (IOException ioe) {
            log.error("NIO [file] errors: " + ioe.getLocalizedMessage());
        }
    }

    /**
     * New I/O file system functionality
     */
    public void invokeNIOFileSystem() {
        // FileSystem - an interface to a file system
        // It is the factory for objects to access files and other objects in the file system

        log.info(" ------ File System");
        // default file system
        FileSystem fileSystem = FileSystems.getDefault();
        try {
            log.info(" * File System separator: " + fileSystem.getSeparator());
            // file stores
            var fileStores = fileSystem.getFileStores();
            log.info(" * File System file stores:");
            fileStores.forEach(
                (fs) -> {
                    try {
                        log.info("   - File store: " + fs.toString());
                        log.info("     - name: " + fs.name());
                        log.info("     - type: " + fs.type());
                        log.info("     - read only: " + fs.isReadOnly());
                        log.info("     - block size: " + fs.getBlockSize());
                        log.info("     - total space: " + fs.getTotalSpace());
                        log.info("     - usable space: " + fs.getUsableSpace());
                        log.info("     - unallocated space: " + fs.getUnallocatedSpace());
                    } catch (Exception e) {
                        log.error("NIO file store errors: " + e.getLocalizedMessage());
                    }
                }
            );
            // root directories
            var rootDirectories = fileSystem.getRootDirectories();
            rootDirectories.forEach(
                (rd) -> log.info("   - Root directory: " + rd.toString())
            );
            // user principal
            var user = System.getProperty("user.name");
            var userPrincipalLookupService = fileSystem.getUserPrincipalLookupService();
            var userPrincipal = userPrincipalLookupService.lookupPrincipalByName(user);
            log.info(" * User principal: " + userPrincipal.getName());
        } catch (IOException ioe) {
            log.error("NIO file system errors: " + ioe.getLocalizedMessage());
        }
        // custom file system
        try {
            URL resourceUrl = getClass().getClassLoader().getResource("file");
            assert resourceUrl != null : "Text file has not been found!";
            File resource = new File(resourceUrl.getFile());
            log.info(" * Custom file system resources: " + resource.getAbsolutePath());
            var resFileSystem = FileSystems.newFileSystem(resource.toPath(), System.getenv());
            log.info(" * Resource file system: " + resFileSystem.toString());
        } catch (Exception e) {
            log.error("NIO [file system] errors: " + e.getLocalizedMessage());
        }
    }

    /**
     * New I/O buffer functionality
     */
    public void invokeNIOBuffer() {
        // Buffer - is a linear, finite sequence of elements of a specific primitive type.
        // Buffer - the essential properties: capacity, limit, position.
        // Buffer - capacity: is the number of elements it contains,
        // Buffer - capacity: is never negative and never changes.
        // Buffer -    limit: is the index of the first element that should not be read or written,
        // Buffer -    limit:  is never negative and is never greater than its capacity.
        // Buffer - position: is the index of the next element to be read or written,
        // Buffer - position: is never negative and is never greater than its limit.

        log.info(" ------ Buffer");
        Buffer buffer = null;
        ByteBuffer byteBuffer = null;

        // empty buffer
        ByteBuffer emptyBuffer = ByteBuffer.allocate(5);
        var first = emptyBuffer.get();
        var clear = emptyBuffer.clear();
        int capacity = emptyBuffer.capacity();
        int limit = emptyBuffer.limit();
        int position = emptyBuffer.position();
        log.info("NIO [byte buffer]: buffer: " + emptyBuffer);
        log.info("NIO [byte buffer]: capacity: {}, position: {}, limit: {}", capacity, position, limit);
        log.info("NIO [byte buffer]: content: " + Arrays.toString(emptyBuffer.array()));

        // byte buffer - get
        final ByteBuffer bb = ByteBuffer.wrap("Hello world!".getBytes(Charset.defaultCharset()));
        final byte[] output = new byte[5];
        bb.get(output);
        var content = new String(output, Charset.defaultCharset());
        // assertions
        assert "Hello".equals(content) : "check#1: ten bytes from buffer";
        assert 5 == bb.position() : "check#2: position of byte buffer";
    }

    /**
     * New I/O channel functionality
     */
    public void invokeNIOChannel() {
        // Channel -  an open connection to an entity such as a hardware device, a file, a network socket, or a program component
        //            that is capable of performing one or more distinct I/O operations, for example reading or writing.
        // rewind() - In general, you must reset the buffer between read and write operations

        log.info(" ------ Channel");

        // read
        {
            // path
            Path path = null;
            try {
                URL sourceUrl = getClass().getClassLoader().getResource("file/source.txt");
                assert sourceUrl != null : "Source file has not been found!";
                path = Path.of(sourceUrl.toURI());
            } catch (Exception e) {
                log.error("NIO [channel] errors: " + e.getLocalizedMessage());
            }

            // Algorithm [portion bytes]:
            // 1. Path.of() - get file path
            // 2. Files.newByteChannel() - get channel
            // 3. ByteBuffer allocate(int capacity) - get buffer
            // 4. int read(ByteBuffer buf) - read buffer

            // channel - seekable - read step-by-step
            var builder = new StringBuilder();
            try (SeekableByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ)) {
                // buffer
                ByteBuffer buffer = ByteBuffer.allocate(16);
                // read
                int count;
                do {
                    // content bytes: 38 = 16 + 16 + 6
                    count = channel.read(buffer);
                    if (count != -1) {
                        buffer.rewind();
                        for (int i = 0; i < count; i++) {
                            builder.append((char) buffer.get());
                        }
                    }
                } while (count != -1);
                log.info(" * Channel read [portion]: " + builder.toString());
            } catch (Exception e) {
                log.error("NIO [channel] read errors: " + e.getLocalizedMessage());
            }

            // Algorithm [all bytes]:
            // 1. Path.of() - get file path
            // 2. Files.newByteChannel() - get channel
            // 3. MappedByteBuffer map() - get buffer
            // 4. buffer.get() - get buffer

            // channel - file - read mapped - entire
            builder = new StringBuilder();
            try (FileChannel channel = (FileChannel) Files.newByteChannel(path, StandardOpenOption.READ)) {
                var size = channel.size();
                // map - entire file content
                MappedByteBuffer buffer = channel.map(
                    FileChannel.MapMode.READ_ONLY,
                    0,
                    size
                );
                for (int i = 0; i < size; i++) {
                    builder.append((char) buffer.get());
                }
                log.info(" * Channel read [map]: " + builder.toString());
            } catch (Exception e) {
                log.error("NIO [channel] read errors: " + e.getLocalizedMessage());
            }
        }

        // write
        {
            // path
            Path path = null;
            try {
                URL channelUrl = getClass().getClassLoader().getResource("file");
                assert channelUrl != null : "Resource file has not been found!";
                File file = new File(channelUrl.getPath(), "channel.txt");
                path = file.toPath();
            } catch (Exception e) {
                log.error("NIO [channel] write errors: " + e.getLocalizedMessage());
            }

            // Algorithm [portion bytes]:
            // 1. Path.of() - get file path
            // 2. Files.newByteChannel() - get channel
            // 3. ByteBuffer allocate(int capacity) - get buffer
            // 4. int write(ByteBuffer buf) - write buffer

            // channel - file - write step-by-step
            int content = 38;
            try (FileChannel channel = (FileChannel) Files.newByteChannel(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
                ByteBuffer buffer = ByteBuffer.allocate(content);
                for (int i = 0; i < content; i++) {
                    buffer.put((byte) ('A' + i));
                }
                buffer.rewind();
                var write = channel.write(buffer);
                log.info(" * Channel write [portion]: " + write);
                var delete = path.toFile().delete();
            } catch (Exception e) {
                log.error("NIO [channel] write errors: " + e.getLocalizedMessage());
            }

            // Algorithm [all bytes]:
            // 1. Path.of() - get file path
            // 2. Files.newByteChannel() - get channel
            // 3. MappedByteBuffer map() - get buffer
            // 4. buffer.get() - get buffer

            // channel - file - write mapped - entire
            try (
                FileChannel channel = (FileChannel) Files.newByteChannel(
                    path,
                    StandardOpenOption.READ,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE
                )
            ) {
                // map
                MappedByteBuffer buffer = channel.map(
                    FileChannel.MapMode.READ_WRITE,
                    0,
                    content
                );
                for (int i = 0; i < content; i++) {
                    buffer.put((byte) ('Z' + i));
                }
                log.info(" * Channel write [map]: " + buffer.position());
            } catch (Exception e) {
                log.error("NIO [channel] write errors: " + e.getLocalizedMessage());
            }
        }
    }

    /**
     * New I/O stream functionality
     */
    public void invokeNIOStream() {
        // Stream - an input source or an output destination

        log.info(" ------ Stream");

        URL streamUrl = getClass().getClassLoader().getResource("file");

        // path
        Path path = null;
        try {
            assert streamUrl != null : "Resource file has not been found!";
            File file = new File(streamUrl.getPath(), "source.txt");
            path = file.toPath();
        } catch (Exception e) {
            log.error("NIO [stream] write errors: " + e.getLocalizedMessage());
        }

        // input stream - read file
        try (InputStream fis = new BufferedInputStream(Files.newInputStream(path))) {
            StringBuilder sb = new StringBuilder();
            int i;
            do {
                i = fis.read();
                if (i != -1) {
                    sb.append((char) i);
                }
            } while (i != -1);
            log.info(" * Stream read: " + sb.toString());
        } catch (Exception e) {
            log.error("NIO [stream][input]: write errors: " + e.getLocalizedMessage());
        }

        // output stream - write file
        File file = new File(streamUrl.getPath(), "stream.txt");
        try (OutputStream fos = new BufferedOutputStream(Files.newOutputStream(file.toPath()))) {
            int content = 26;
            for (int i = 0; i < content; i++) {
                fos.write((byte) ('A' + i));
            }
            fos.flush();
            log.info(" * Stream write: " + file.length());
            var delete = path.toFile().delete();
        } catch (Exception e) {
            log.error("NIO [stream][output] write errors: " + e.getLocalizedMessage());
        }

        // directory stream - all files
        File stream = new File(streamUrl.getFile());
        // filter
        DirectoryStream.Filter<Path> filter = Files::isWritable;
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(stream.toPath(), filter)) {
            log.info(" * Stream directory: " + stream.getAbsolutePath());
            for (Path entry : ds) {
                var attributes = Files.readAttributes(entry, BasicFileAttributes.class);
                var info = attributes.isDirectory() ? "<DIR>" : "<FILE>";
                log.info("   " + info + " " + entry.getFileName() + ", size:" + attributes.size());
            }
        } catch (NotDirectoryException e) {
            log.error("NIO [stream][directory] folder not found: " + e.getLocalizedMessage());
        } catch (Exception e) {
            log.error("NIO [stream][directory] write errors: " + e.getLocalizedMessage());
        }

        // file visitor - walk file tree
        class JKitVisitor extends SimpleFileVisitor<Path> {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                log.info("   - " + path);
                return super.visitFile(path, attrs);
            }
        }
        try {
            File tree = new File(streamUrl.getFile()).getParentFile();
            log.info(" * Stream tree [path]: " + tree);
            Files.walkFileTree(tree.toPath(), new JKitVisitor());
        } catch (Exception e) {
            log.error("NIO [stream][tree] write errors: " + e.getLocalizedMessage());
        }
    }

    /**
     * New I/O selector functionality
     */
    public void invokeNIOSelector() {
        // Selector -  a component which can examine one or more channels,
        // and determine which channels are ready for e.g. reading or writing
        // Uses in client/server apps: ServerSocketChannel as SelectableChannel

        try {
            Selector selector = Selector.open();
            int readyChannels = selector.selectNow();
            while (readyChannels > 0) {
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        log.info("Key [accept]: Connection was accepted, interest {}", key.interestOps());
                    } else if (key.isConnectable()) {
                        log.info("Key [connect]: Connection was established, interest {}", key.interestOps());
                    } else if (key.isReadable()) {
                        log.info("Key [read]: Channel is ready for reading, interest {}", key.interestOps());
                    } else if (key.isWritable()) {
                        log.info("Key [write]: Channel is ready for writing, interest {}", key.interestOps());
                    }
                    keyIterator.remove();
                }
                readyChannels--;
            }
            selector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
