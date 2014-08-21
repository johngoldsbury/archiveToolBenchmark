package com.benchmark;



	import java.io.PrintWriter;

	import org.apache.commons.cli.CommandLine;
	import org.apache.commons.cli.CommandLineParser;
	import org.apache.commons.cli.GnuParser;
	import org.apache.commons.cli.HelpFormatter;
	import org.apache.commons.cli.Option;
	import org.apache.commons.cli.ParseException;
	import org.apache.commons.logging.Log;
	import org.apache.commons.logging.LogFactory;

	import uk.gov.nationalarchives.droid.command.action.CommandExecutionException;
	import uk.gov.nationalarchives.droid.command.action.CommandFactory;
	import uk.gov.nationalarchives.droid.command.action.CommandFactoryImpl;
	import uk.gov.nationalarchives.droid.command.action.CommandLineException;
	import uk.gov.nationalarchives.droid.command.action.CommandLineParam;
	import uk.gov.nationalarchives.droid.command.action.CommandLineSyntaxException;
	import uk.gov.nationalarchives.droid.command.context.GlobalContext;
	import uk.gov.nationalarchives.droid.command.context.SpringUiContext;
	import uk.gov.nationalarchives.droid.core.interfaces.config.RuntimeConfig;

	/**
	 * The DROID command line user-interface.
	 * 
	 * @author rflitcroft, Alok Kumar Dash
	 * 
	 */
	public final class DroidCLI {

	    /** Options message. */
	    public static final String USAGE = "droid [options]";
	    /** Wrap width. */
	    public static final int WRAP_WIDTH = 120;
	    
	    /**Logger Log4j.*/
	    private Log log = LogFactory.getLog(this.getClass());
	    //private static Logger log = Logger.getLogger(DroidCommandLine.class);
	    


	    private final String[] args;
	    private GlobalContext context = new SpringUiContext();
	    


	    
	    private CommandFactory commandFactory;
	    private CommandLine cli;
	    
	    /**
	     * Default constructor.
	     * 
	     * @param args
	     *            the command line arguments
	     */
	    DroidCLI(final String[] args) {
	        this.args = args;
	    }
	    
	    /**
	     * 
	     * @return GlobalContext object
	     */
	    public GlobalContext getContext() {
	        return context;
	    }

	    /**
	     * 
	     * @param context object
	     */
	    public void setContext(GlobalContext context) {
	        this.context = context;
	    }
	    
	    /**
	     * Runs the command line interface.
	     * 
	     * @throws CommandLineException
	     *             if the command line failed for any reason
	     */
	    public void run() throws CommandLineException {
	        // log.info("Starting DROID.");
	        CommandLineParser parser = new GnuParser();

	        try {
	            cli = parser.parse(CommandLineParam.options(), args);

	            String logThreshold = "INFO";
	            if (cli.hasOption(CommandLineParam.QUIET.toString())) {
	                logThreshold = "ERROR";
	            }
	            System.setProperty("consoleLogThreshold", logThreshold);

	            CommandLineParam option = null;
	            for (Option opt : cli.getOptions()) {
	                option = CommandLineParam.TOP_LEVEL_COMMANDS.get(opt.getOpt());
	                if (option != null) {
	                    break;

	                }
	            }

	            if (option != null) {
	                option.getCommand(commandFactory, cli).execute();
	            } else {
	                throw new CommandLineSyntaxException(
	                        "No command line options specified (use -h to see all available options)");
	            }
	        } catch (ParseException pe) {
	            throw new CommandLineSyntaxException(pe);
	        }

	        // finally {
	        // log.info("Closing DROID.");
	        // }
	    }

	    /**
	     * Main method for command line.
	     * 
	     * @param args
	     *            the command line arguments
	     * @throws CommandLineException 
	     * @throws CommandExecutionException 
	     */
	    public static void main(final String[] args) throws CommandLineException {

	        RuntimeConfig.configureRuntimeEnvironment();

	        GlobalContext context = new SpringUiContext();
	        
	        int returnCode = 0;
	    
	        DroidCLI commandLine = new DroidCLI(args);
	        returnCode = commandLine.processExecution();



	        
	    }

	    /**
	     * 
	     * @return a status code 0 success otherwise 1
	     * @throws CommandLineException 
	     */
	    public int processExecution() throws CommandLineException {
	        
	        RuntimeConfig.configureRuntimeEnvironment();
	        PrintWriter out = new PrintWriter(System.out);
	        
	        final CommandFactoryImpl localCommandFactory = new CommandFactoryImpl(context, out);
	        
	        this.setCommandFactory(localCommandFactory);

	        int returnCode = 0;

	        try {

	            run();
	            
//	            out.close();
//	            context.close();

	        } catch (CommandExecutionException ceex) {
	            returnCode = 1;
	            PrintWriter err = new PrintWriter(System.err);
	            HelpFormatter formatter = new HelpFormatter();
	            formatter.printWrapped(err, WRAP_WIDTH, ceex.getMessage());
	            err.close();
	            log.error("Droid Execution Error", ceex);
	            throw ceex;

	        } catch (CommandLineException clex) {
	            returnCode = 1;
	            PrintWriter err = new PrintWriter(System.err);
	            HelpFormatter formatter = new HelpFormatter();
	            formatter.printWrapped(err, WRAP_WIDTH, clex.getMessage());
	            err.close();
	            log.error("Droid CommandLineException", clex);
	            throw clex;
	        }

	        return returnCode;
	    }

	    /**
	     * 
	     * @return CommandFactory object
	     */
	    public CommandFactory getCommandFactory() {
	        return commandFactory;
	    }

	    /**
	     * 
	     * @param commandFactory 
	     */
	    public void setCommandFactory(CommandFactory commandFactory) {
	        this.commandFactory = commandFactory;
	    }

	    /**
	     * 
	     * @return the command line
	     */
	    public CommandLine getCommandLine() {
	        return cli;
	    }

	}



