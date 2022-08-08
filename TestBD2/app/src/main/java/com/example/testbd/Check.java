

        package com.example.testbd;

        import android.content.Context;
        import android.widget.Toast;

        public class Check {
            public boolean ISNull(String name, String KVT, String Oborot,String i, String KPD, String KOEF, String In, String Om, String Old ,Context context){
                boolean trus = true;
                if (name.equals("")){
                    Toast.makeText(context,R.string.Action_check, Toast.LENGTH_LONG).show();
                    trus = false;}
                else {
                    if (KVT.equals("")) {
                        Toast.makeText(context, R.string.Action_check, Toast.LENGTH_LONG).show();
                        trus = false;
                    } else {
                        if (Oborot.equals("")) {
                            Toast.makeText(context, R.string.Action_check, Toast.LENGTH_LONG).show();
                            trus = false;
                        } else {
                            if (i.equals("")) {
                                Toast.makeText(context, R.string.Action_check, Toast.LENGTH_LONG).show();
                                trus = false;
                            } else {
                                if (KPD.equals("")) {
                                    Toast.makeText(context, R.string.Action_check, Toast.LENGTH_LONG).show();
                                    trus = false;
                                }else {
                                    if (KOEF.equals("")) {
                                        Toast.makeText(context, R.string.Action_check, Toast.LENGTH_LONG).show();
                                        trus = false;
                                    } else {
                                        if (In.equals("")) {
                                            Toast.makeText(context, R.string.Action_check, Toast.LENGTH_LONG).show();
                                            trus = false;
                                        }else {
                                            if (Om.equals("")) {
                                                Toast.makeText(context, R.string.Action_check, Toast.LENGTH_LONG).show();
                                                trus = false;
                                            }else {
                                                if (Old.equals("")) {
                                                    Toast.makeText(context, R.string.Action_check, Toast.LENGTH_LONG).show();
                                                    trus = false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }}

                return trus;
            }
            public boolean ISNul(String name, String KVT, String Oborot,Context context){
                boolean trus = true;
                if (name.equals("")){
                    Toast.makeText(context,R.string.Action_check, Toast.LENGTH_LONG).show();
                    trus = false;}
                else {
                    if (KVT.equals("")) {
                        Toast.makeText(context, R.string.Action_check, Toast.LENGTH_LONG).show();
                        trus = false;
                    } else {
                        if (Oborot.equals("")) {
                            Toast.makeText(context, R.string.Action_check, Toast.LENGTH_LONG).show();
                            trus = false;
                        }}}
                return trus;
            }

            public String Regu(String in){
                String out;
                int index = in.indexOf('.');
                out = in.substring(0, index+3);
                return out;
            }

        }