/**
 * 
 */
package cn.log.app.database.vo;

/**
 * @author zouqone
 * date 2014年5月11日   下午12:21:15
 * 触发器
 */
public class TriggerVo {

	/**
	 * 
	 */
	private String trigger_catalog;
	
	/**
	 * 
	 */
	private String trigger_schema;

	/**
	 * 
	 */
	private String trigger_name;

	/**
	 * 
	 */
	private String event_manipulation;

	/**
	 * 
	 */
	private String event_object_catalog;

	/**
	 * 
	 */
	private String event_object_schema;

	/**
	 * 
	 */
	private String event_object_table;

	/**
	 * 
	 */
	private String action_order;

	/**
	 * 
	 */
	private String action_condition;

	/**
	 * 
	 */
	private String action_statement;

	/**
	 * 
	 */
	private String action_orientation;

	/**
	 * 
	 */
	private String action_timing;

	/**
	 * 
	 */
	private String action_reference_old_table;

	/**
	 * 
	 */
	private String action_reference_new_table;

	/**
	 * 
	 */
	private String action_reference_old_row;

	/**
	 * 
	 */
	private String action_reference_new_row;

	/**
	 * 
	 */
	private String created;

	/**
	 * 
	 */
	private String sql_mode;

	/**
	 * 
	 */
	private String definer;

	/**
	 * 
	 */
	private String character_set_client;

	/**
	 * 
	 */
	private String collation_connection;

	/**
	 * 
	 */
	private String database_collation;

	/**
	 * 
	 */
	private String ddl;
	
	
	/**
	 * 
	 */
	public TriggerVo() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the trigger_catalog
	 */
	public String getTrigger_catalog() {
		return trigger_catalog;
	}


	/**
	 * @param trigger_catalog the trigger_catalog to set
	 */
	public void setTrigger_catalog(String trigger_catalog) {
		this.trigger_catalog = trigger_catalog;
	}


	/**
	 * @return the trigger_schema
	 */
	public String getTrigger_schema() {
		return trigger_schema;
	}


	/**
	 * @param trigger_schema the trigger_schema to set
	 */
	public void setTrigger_schema(String trigger_schema) {
		this.trigger_schema = trigger_schema;
	}


	/**
	 * @return the trigger_name
	 */
	public String getTrigger_name() {
		return trigger_name;
	}


	/**
	 * @param trigger_name the trigger_name to set
	 */
	public void setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
	}


	/**
	 * @return the event_manipulation
	 */
	public String getEvent_manipulation() {
		return event_manipulation;
	}


	/**
	 * @param event_manipulation the event_manipulation to set
	 */
	public void setEvent_manipulation(String event_manipulation) {
		this.event_manipulation = event_manipulation;
	}


	/**
	 * @return the event_object_catalog
	 */
	public String getEvent_object_catalog() {
		return event_object_catalog;
	}


	/**
	 * @param event_object_catalog the event_object_catalog to set
	 */
	public void setEvent_object_catalog(String event_object_catalog) {
		this.event_object_catalog = event_object_catalog;
	}


	/**
	 * @return the event_object_schema
	 */
	public String getEvent_object_schema() {
		return event_object_schema;
	}


	/**
	 * @param event_object_schema the event_object_schema to set
	 */
	public void setEvent_object_schema(String event_object_schema) {
		this.event_object_schema = event_object_schema;
	}


	/**
	 * @return the event_object_table
	 */
	public String getEvent_object_table() {
		return event_object_table;
	}


	/**
	 * @param event_object_table the event_object_table to set
	 */
	public void setEvent_object_table(String event_object_table) {
		this.event_object_table = event_object_table;
	}


	/**
	 * @return the action_order
	 */
	public String getAction_order() {
		return action_order;
	}


	/**
	 * @param action_order the action_order to set
	 */
	public void setAction_order(String action_order) {
		this.action_order = action_order;
	}


	/**
	 * @return the action_condition
	 */
	public String getAction_condition() {
		return action_condition;
	}


	/**
	 * @param action_condition the action_condition to set
	 */
	public void setAction_condition(String action_condition) {
		this.action_condition = action_condition;
	}


	/**
	 * @return the action_statement
	 */
	public String getAction_statement() {
		return action_statement;
	}


	/**
	 * @param action_statement the action_statement to set
	 */
	public void setAction_statement(String action_statement) {
		this.action_statement = action_statement;
	}


	/**
	 * @return the action_orientation
	 */
	public String getAction_orientation() {
		return action_orientation;
	}


	/**
	 * @param action_orientation the action_orientation to set
	 */
	public void setAction_orientation(String action_orientation) {
		this.action_orientation = action_orientation;
	}


	/**
	 * @return the action_timing
	 */
	public String getAction_timing() {
		return action_timing;
	}


	/**
	 * @param action_timing the action_timing to set
	 */
	public void setAction_timing(String action_timing) {
		this.action_timing = action_timing;
	}


	/**
	 * @return the action_reference_old_table
	 */
	public String getAction_reference_old_table() {
		return action_reference_old_table;
	}


	/**
	 * @param action_reference_old_table the action_reference_old_table to set
	 */
	public void setAction_reference_old_table(String action_reference_old_table) {
		this.action_reference_old_table = action_reference_old_table;
	}


	/**
	 * @return the action_reference_new_table
	 */
	public String getAction_reference_new_table() {
		return action_reference_new_table;
	}


	/**
	 * @param action_reference_new_table the action_reference_new_table to set
	 */
	public void setAction_reference_new_table(String action_reference_new_table) {
		this.action_reference_new_table = action_reference_new_table;
	}


	/**
	 * @return the action_reference_old_row
	 */
	public String getAction_reference_old_row() {
		return action_reference_old_row;
	}


	/**
	 * @param action_reference_old_row the action_reference_old_row to set
	 */
	public void setAction_reference_old_row(String action_reference_old_row) {
		this.action_reference_old_row = action_reference_old_row;
	}


	/**
	 * @return the action_reference_new_row
	 */
	public String getAction_reference_new_row() {
		return action_reference_new_row;
	}


	/**
	 * @param action_reference_new_row the action_reference_new_row to set
	 */
	public void setAction_reference_new_row(String action_reference_new_row) {
		this.action_reference_new_row = action_reference_new_row;
	}


	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}


	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}


	/**
	 * @return the sql_mode
	 */
	public String getSql_mode() {
		return sql_mode;
	}


	/**
	 * @param sql_mode the sql_mode to set
	 */
	public void setSql_mode(String sql_mode) {
		this.sql_mode = sql_mode;
	}


	/**
	 * @return the definer
	 */
	public String getDefiner() {
		return definer;
	}


	/**
	 * @param definer the definer to set
	 */
	public void setDefiner(String definer) {
		this.definer = definer;
	}


	/**
	 * @return the character_set_client
	 */
	public String getCharacter_set_client() {
		return character_set_client;
	}


	/**
	 * @param character_set_client the character_set_client to set
	 */
	public void setCharacter_set_client(String character_set_client) {
		this.character_set_client = character_set_client;
	}


	/**
	 * @return the collation_connection
	 */
	public String getCollation_connection() {
		return collation_connection;
	}


	/**
	 * @param collation_connection the collation_connection to set
	 */
	public void setCollation_connection(String collation_connection) {
		this.collation_connection = collation_connection;
	}


	/**
	 * @return the database_collation
	 */
	public String getDatabase_collation() {
		return database_collation;
	}


	/**
	 * @param database_collation the database_collation to set
	 */
	public void setDatabase_collation(String database_collation) {
		this.database_collation = database_collation;
	}


	/**
	 * @return the ddl
	 */
	public String getDdl() {
		return ddl;
	}


	/**
	 * @param ddl the ddl to set
	 */
	public void setDdl(String ddl) {
		this.ddl = ddl;
	}

	
}
