package com.tjhruska.mc.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tjhruska.mc.database.BaseDomain;
import com.tjhruska.mc.database.DaoDomain;
import com.tjhruska.mc.database.restrictions.Restriction;
import com.tjhruska.mc.database.restrictions.RestrictionType;
import com.tjhruska.mc.database.restrictions.Restrictions;


@Controller
public class CreateReadUpdateDeleteController implements ApplicationContextAware{

    private static final Logger logger = LoggerFactory.getLogger(CreateReadUpdateDeleteController.class);
    
    private ApplicationContext applicationContext;
    
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException 
    {
        this.applicationContext = applicationContext;
    }
    
    @RequestMapping(value="/test/{parm1}", method=RequestMethod.GET)
    public ModelAndView home(@PathVariable String parm1) {
        logger.info("Welcome home!");
        ModelAndView model = new ModelAndView();
        model.addObject("parm1", parm1); 
        model.setViewName("home");
        return model;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value="/crud/{table}", method=RequestMethod.POST)
    public void create(@PathVariable String table, ServletRequest request, ServletResponse response, @RequestBody String requestBody){

        StringBuilder responseBody = new StringBuilder("");

        DaoDomain<BaseDomain> daoDomain = null;
        Class domainClass = null;
        
        try{
            daoDomain = (DaoDomain<BaseDomain>)applicationContext.getBean(table+"Dao");
            domainClass = daoDomain.getDomainClass();
        }catch(Throwable t){
            writeJsonErrorToResponse("Failed to find dao for: " + table, t, response);
            return;
        }
        
        try{

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new StdDateFormat());
            String jsonEncoded = requestBody;
            String jsonDecoded = URLDecoder.decode(jsonEncoded, "UTF-8");
            jsonDecoded = jsonDecoded.substring(0, jsonDecoded.indexOf('}') + 1);
            //BaseDomain updatedEntity =  objectMapper.readValue(jsonDecoded, domainClass);
            Object updatedEntity =  objectMapper.readValue(jsonDecoded, domainClass);

            //writeOutput(responseBody, response);
            writeJsonErrorToResponse("Create not yet implemented.", response);
            logger.info("create called for domain object:{}.\n    with requestBody:{}\n    encoded into:{}\n    decoded into:{}\n    response:{}", 
                new Object[]{table, requestBody, jsonEncoded, jsonDecoded, responseBody.toString()});
        }catch (Exception e){
            writeJsonErrorToResponse("Failed to decode json to :" + table, e, response);
        }
    }

    @RequestMapping(value="/crud/{tableName}", method=RequestMethod.GET)
    public void read(@PathVariable String tableName, ServletResponse response){
        read(tableName, null, response);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value="/crud/{tableName}/{id}", method=RequestMethod.GET)
    public void read(@PathVariable String tableName, @PathVariable Integer id, ServletResponse response){
        StringBuilder output = new StringBuilder();

        DaoDomain<BaseDomain> daoDomain = null;
        List<BaseDomain> domainList = new ArrayList<BaseDomain>();
        try{
            daoDomain = (DaoDomain<BaseDomain>)applicationContext.getBean(tableName + "Dao");
            if (id != null)
                domainList.add(daoDomain.findByPK(id));
            else
                domainList = daoDomain.findAll();
                
        }catch(Exception e){
            if (id != null){
                output.append("Failed to retrieve data for ").append(tableName).append(" with id ").append(id).append(" and exception: ").append(e.getMessage());
                logger.error("Failed to retrieve data for " + tableName, e);
            }else{
                output.append("Failed to retrieve data for ").append(tableName).append(" and exception: ").append(e.getMessage());
                logger.error("Failed to retrieve data for " + tableName + ", id " + id, e);
            }
                
        }
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new StdDateFormat());
        try {
            StringBuilder json = new StringBuilder(objectMapper.writeValueAsString(domainList));
            output.append("{\"results\": ").append(domainList.size()).append(",\"rows\": ").append(json).append("}");
            logger.info("read called for table {}, id {}, {} records returned.", 
                    new Object[]{tableName, id, domainList.size()});
        } catch (Exception e) {
            output.append("failed to convert domain object to json with error:\n").append(e.getMessage());
            logger.error("failed to convert domain object of type {} with id {} to json", tableName, id);
            logger.error("with error:\n{}", e);
        }
        writeOutput(output, response);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/crud/{table}/{id}", method=RequestMethod.PUT)
    public void update(@PathVariable String table, @PathVariable Integer id, ServletRequest request, ServletResponse response, @RequestBody String requestBody){

        StringBuilder responseBody = new StringBuilder("");

        DaoDomain<BaseDomain> daoDomain = null;
        Class domainClass = null;
        BaseDomain baseDomain = null;
        try{
            daoDomain = (DaoDomain<BaseDomain>)applicationContext.getBean(table + "Dao");
            domainClass = daoDomain.getDomainClass();
            if (id != null)
                baseDomain = daoDomain.findByPK(id);
            else {
                writeJsonErrorToResponse("id missing from request to update record", response);
                return;
            }
            if (baseDomain == null){
                writeJsonErrorToResponse(new StringBuilder("failed to find ").append(table).
                    append(" record with id ").append(id).append(" aborting update."), response);
                return;
            }
                
        }catch(Throwable t){
            writeJsonErrorToResponse("Failed to find dao for: " + table, t, response);
            return;
        }
        
        try{

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new StdDateFormat());
            String jsonEncoded = requestBody;
            String jsonDecoded = URLDecoder.decode(jsonEncoded, "UTF-8");
            jsonDecoded = jsonDecoded.substring(0, jsonDecoded.indexOf('}') + 1);
            
            //should we be updating the already loaded value, or just confirming it existed with that id?
            Object updatedEntity =  objectMapper.readValue(jsonDecoded, domainClass);

            if (!((BaseDomain)updatedEntity).getId().equals(id)){
                writeJsonErrorToResponse(new StringBuilder("update for ").append(table).append(" record with url id ")
                    .append(id).append(" didn't match id from json request body ").append(((BaseDomain)updatedEntity).getId()), response);
                return;
            }
            //writeOutput(responseBody, response);
            writeJsonErrorToResponse("Update not yet implemented.", response);
            logger.info("update called for domain object:{}.\n    with requestBody:{}\n    encoded into:{}\n    decoded into:{}\n    response:{}", 
                new Object[]{table, requestBody, jsonEncoded, jsonDecoded, responseBody.toString()});
        }catch (Exception e){
            writeJsonErrorToResponse("Failed to decode json to :" + table, e, response);
        }
    }

    @RequestMapping(value="/crud/{table}/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable String table, @PathVariable Integer id, ServletResponse response){
        writeOutput(new StringBuilder("fake response for deleting a record in table ").append(table).append(" for id ").append(id), response);
        logger.info("delete called for table {}, id {}.", table, id);
        throw(new RuntimeException());
    }
    
    @RequestMapping(value="combobox/{comboBoxSource}/{includeNull}/{key}/{description}", method=RequestMethod.GET)
    public void getComboBox(@PathVariable String comboBoxSource, @PathVariable Boolean includeNull,
        @PathVariable String key, @PathVariable String description, ServletResponse response){
        getComboBox(comboBoxSource, includeNull, key, description, response, null, null, null, null);
    }
    
    @RequestMapping(value="combobox/{comboBoxSource}/{includeNull}/{key}/{description}/{filter1}/{filterValue1}", method=RequestMethod.GET)
    public void getComboBox(@PathVariable String comboBoxSource, @PathVariable Boolean includeNull,
        @PathVariable String key, @PathVariable String description, ServletResponse response,
        @PathVariable String filter1, @PathVariable String filterValue1)
    {
        getComboBox(comboBoxSource, includeNull, key, description, response, filter1, filterValue1, null, null);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    //@RequestMapping(value="combobox/{tableName}/{comboBoxName}/{id}", method=RequestMethod.GET)
    @RequestMapping(value="combobox/{comboBoxSource}/{includeNull}/{key}/{description}/{filter1}/{filterValue1}/{filter2}/{filterValue2}", method=RequestMethod.GET)
    public void getComboBox(@PathVariable String comboBoxSource, @PathVariable Boolean includeNull,
        @PathVariable String key, @PathVariable String description, ServletResponse response,
        @PathVariable String filter1, @PathVariable String filterValue1,
        @PathVariable String filter2, @PathVariable String filterValue2)
    {
        StringBuilder output = new StringBuilder();
        
        DaoDomain<BaseDomain> daoDomain = null;
        Class domainClass = null;
        Restriction restriction = null;
        
        try{
            daoDomain = (DaoDomain<BaseDomain>)applicationContext.getBean(comboBoxSource+"Dao");
            domainClass = daoDomain.getDomainClass();
        }catch(Throwable t){
            writeJsonErrorToResponse("Failed to find dao for comboBoxSource: " + comboBoxSource, t, response);
            return;
        }

        Map<String,String> filters = new HashMap<String,String>();
        if (filter1 != null && filterValue1 != null) filters.put(filter1, filterValue1);
        if (filter2 != null && filterValue2 != null) filters.put(filter2, filterValue2);
    
        for (Map.Entry<String,String> filter : filters.entrySet()){
            Class filterClass = null;
            String filterMethodName = String.format("get%s%s", filter.getKey().substring(0, 1).toUpperCase(), filter.getKey().substring(1));
            String altFilterMethodName = String.format("is%s%s", filter.getKey().substring(0, 1).toUpperCase(), filter.getKey().substring(1));
            try {
                for (Method filterMethod : Arrays.asList(domainClass.getMethods())){
                    if (filterMethod.getName().equals(filterMethodName))
                        filterClass = filterMethod.getReturnType();
                    else if (filterMethod.getName().equals(altFilterMethodName))
                        filterClass = filterMethod.getReturnType();
                }
            } catch (Throwable t) {
                writeJsonErrorToResponse("Failed to find field " + filter.getKey() 
                    + " as a field on " + comboBoxSource + " to use as a filter.", t, response);
                return;
            }
            if (filterClass == null){
                writeJsonErrorToResponse("Failed to find field " + filter.getKey() 
                    + " as a field on " + comboBoxSource + " to use as a filter.", response);
                return;
            }
        
            String value = filter.getValue();
            String[] values = null;
            RestrictionType restrictionType = RestrictionType.parseURLRestrictionType(value);
            if (restrictionType != null){
                value = value.substring(restrictionType.getOperator().length());
                switch (restrictionType) {
                case IN:
                    values = value.split(",");
                    break;
                }
            }
            
            Object filterValue = null;
            Object[] filterValues = null;
            
            List<Object> tempValues = new ArrayList<Object>();
            int loopLimit = (values == null ? 1: values.length);
            
            for (int i = 0; i < loopLimit; i++){
                
                String currentValue = (values == null ? value : values[i]);
                if (filterClass.equals(Short.class)){
                    logger.info("parsed a short");
                    filterValue = Short.parseShort(currentValue);
                } else if (filterClass.equals(Integer.class)){
                    logger.info("parsed a integer");
                    filterValue = Integer.parseInt(currentValue);
                } else if (filterClass.equals(Long.class)){
                    logger.info("parsed a long");
                    filterValue = Long.parseLong(currentValue);
                } else if (filterClass.equals(Float.class)){
                    logger.info("parsed a float");
                    filterValue = Float.parseFloat(currentValue);
                } else if (filterClass.equals(Double.class)){
                    logger.info("parsed a double");
                    filterValue = Double.parseDouble(currentValue);
                } else if (filterClass.equals(Boolean.class)){
                    logger.info("parsed a boolean");
                    filterValue = Boolean.parseBoolean(currentValue);
                } else {
                    logger.info("parsed a string");
                    filterValue = currentValue;
                }
                
                if (values != null)
                    tempValues.add(filterValue);
            }
            if (values != null)
                filterValues = tempValues.toArray();
        
            Restriction tempRestriction = null;

            if (restrictionType == null || restrictionType.equals(RestrictionType.EQUALS))
                tempRestriction = Restrictions.eq(filter.getKey(), filterValue);
            else if (restrictionType.equals(RestrictionType.GREATER_THAN))
                tempRestriction = Restrictions.gt(filter.getKey(), filterValue);
            else if (restrictionType.equals(RestrictionType.GREATER_THAN_OR_EQUALS))
                tempRestriction = Restrictions.ge(filter.getKey(), filterValue);
            else if (restrictionType.equals(RestrictionType.IN))
                tempRestriction = Restrictions.in(filter.getKey(), filterValues);
            else if (restrictionType.equals(RestrictionType.INSENSITIVE_CASE_LIKE))
                tempRestriction = Restrictions.ilike(filter.getKey(), filterValue);
            else if (restrictionType.equals(RestrictionType.IS_NOT_NULL))
                tempRestriction = Restrictions.isNotNull(filter.getKey());
            else if (restrictionType.equals(RestrictionType.IS_NULL))
                tempRestriction = Restrictions.isNull(filter.getKey());
            else if (restrictionType.equals(RestrictionType.LESS_THAN))
                tempRestriction = Restrictions.lt(filter.getKey(), filterValue);
            else if (restrictionType.equals(RestrictionType.LESS_THAN_OR_EQUALS))
                tempRestriction = Restrictions.le(filter.getKey(), filterValue);
            else if (restrictionType.equals(RestrictionType.NOT_EQUALS))
                tempRestriction = Restrictions.ne(filter.getKey(), filterValue);
            
            if (restriction == null)
                restriction = tempRestriction;
            else
                restriction = Restrictions.and(restriction, tempRestriction);
        }
        
        List<BaseDomain> domainList = new ArrayList<BaseDomain>();
        try{
            if (restriction == null)
                domainList = daoDomain.findAll();
            else
                domainList.addAll(daoDomain.findByCriteria(restriction));
        }catch(Throwable t){
            writeJsonErrorToResponse("Failed to retrieve contents of combox", t, response);
            return;
        }
    
        output.append("{\"results\": ").append(domainList.size()+1).append(",\"rows\": [");
        boolean printedFirstElement = false;
        if (includeNull){
            output.append("{\"key\":null,\"desc\":\"NULL\"}");
            printedFirstElement = true;
        }
        
        String keyMethod = String.format("get%s%s", key.substring(0, 1).toUpperCase(), key.substring(1));
        String descriptionMethod = String.format("get%s%s", description.substring(0, 1).toUpperCase(), description.substring(1));
        
        for (BaseDomain domainObject : domainList){
            String keyValue;
            try {
                keyValue = (domainClass.getMethod(keyMethod, new Class[0]).invoke(domainObject, (Object[])null)).toString();
            } catch (Throwable t) {
                writeJsonErrorToResponse("Failed to call " + keyMethod, t, response);
                return;
            }

            String descriptionValue;
            try {
                descriptionValue = (domainClass.getMethod(descriptionMethod, new Class[0]).invoke(domainObject, (Object[])null)).toString();
            } catch (Throwable t) {
                writeJsonErrorToResponse("Failed to call " + descriptionMethod, t, response);
                return;
            }

            if (printedFirstElement){
                output.append(",");
            }
            output.append("{\"key\":\"").append(keyValue).append("\",\"desc\":\"").append(descriptionValue).append("\"}");
            printedFirstElement = true;
        }
        output.append("]}");
        logger.info("comboBox called for comboBoxSource {}, includeNull {}, filter1 {}:{}, filter2 {}:{}.", 
            new Object[] {comboBoxSource, includeNull.toString(), filter1, filterValue1, filter2, filterValue2});
        writeOutput(output, response);
    }
    
    private void writeOutput(StringBuilder output, ServletResponse response){
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.print(output.append('\n'));
        } catch (IOException e) {
            logger.error("error getting writer", e);
        }
    }
    
    @SuppressWarnings("unused")
	private void writeJsonSuccessToResponse(String message, ServletResponse response){
        StringBuilder output = new StringBuilder("Success: {").append(message).append('}');
        writeOutput(output, response);
    }
    
    private void writeJsonErrorToResponse(String message, ServletResponse response){
        writeJsonErrorToResponse(message, null, response);
    }
    
    private void writeJsonErrorToResponse(StringBuilder message, ServletResponse response){
        writeJsonErrorToResponse(message.toString(), null, response);
    }
    
    @SuppressWarnings("unused")
	private void writeJsonErrorToResponse(StringBuilder message, Throwable t, ServletResponse response){
        writeJsonErrorToResponse(message.toString(), t, response);
    }
    
    private void writeJsonErrorToResponse(String message, Throwable t, ServletResponse response){
        if (t == null)
            logger.error(message);
        else
            logger.error(message, t);
        StringBuilder output = new StringBuilder("Failure: {").append(message).append('\n');
        if (t != null)
            output.append("With exception:").append(t.getMessage());
        output.append("}");
        writeOutput(output, response);    
    }

}
