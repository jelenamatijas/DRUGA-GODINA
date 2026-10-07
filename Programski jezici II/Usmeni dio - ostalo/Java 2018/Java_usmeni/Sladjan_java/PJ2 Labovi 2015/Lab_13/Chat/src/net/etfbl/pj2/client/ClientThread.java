/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.etfbl.pj2.client;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author IGOR
 */
public class ClientThread extends Thread {

    private String selectedUser = "";
    private JList users;
    private JList messages;
    private BufferedReader in;
    private String username;
    //user - messages
    public static HashMap<String, ArrayList<String>> allMessages = new HashMap<>();

    public ClientThread(JList list, JList messages, BufferedReader in, String username) {
        super();
        this.in = in;
        this.users = list;
        this.messages = messages;
        this.username = username;
    }

    public void updateMessageList(String select) {
        if (select != null && select.contains(" (*)")) {
            select = select.split(" (/*)")[0];
        }
        try {
            messages.setListData(new Vector(allMessages.get(select)));
        } catch (NullPointerException e) {
        }
    }

    public void run() {
        users.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                String select = (String) users.getSelectedValue();
                selectedUser = select;
                updateMessageList(select);
            }
        });
        while (true) {
            try {
                String s = in.readLine();
                if (s.startsWith("USERS")) {
                    String[] u = (s.split("#")[1]).split("@");
                    Vector<String> l = new Vector<>(Arrays.asList(u));
                    l.remove(username);
                    users.setListData(l);
                }
                if (s.startsWith("MSG")) {
                    if (s.split("#").length != 3) {
                        continue;
                    }
                    String sender = s.split("#")[1];
                    String message = s.split("#")[2];
                    //update map
                    if (!allMessages.containsKey(sender)) {
                        allMessages.put(sender, new ArrayList<>());
                    }
                    allMessages.get(sender).add("<< " + message);
                    if (selectedUser.equals(sender) || selectedUser.equals(sender + " (*)")) {
                        updateMessageList(sender);
                    } else {
                        //show notification
                        Vector<String> l = new Vector<>();
                        ListModel lm = users.getModel();
                        for (int i = 0; i < lm.getSize(); i++) {
                            String tmp = (String) lm.getElementAt(i);
                            if (tmp.equals(sender)) {
                                tmp += " (*)";
                            }
                            l.add(tmp);
                        }
                        users.setListData(l);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
