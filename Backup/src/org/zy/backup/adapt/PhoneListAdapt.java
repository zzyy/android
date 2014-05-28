package org.zy.backup.adapt;

import org.zy.backup.R;
import org.zy.backup.model.Contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class PhoneListAdapt extends ArrayAdapter<Contact> {

	private LayoutInflater layoutInflater;
	public PhoneListAdapt(Context context) {
		super(context, R.layout.phone_list_item);
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder hodler;
		if(convertView == null){
			convertView = layoutInflater.inflate(R.layout.phone_list_item, parent, false);
			TextView name =  (TextView) convertView.findViewById(R.id.name);
			TextView phoneNumber =  (TextView) convertView.findViewById(R.id.phone_number);
			hodler = new ViewHolder();
			hodler.name = name;
			hodler.phonenumber = phoneNumber;
			convertView.setTag(hodler);
		}else{
			hodler = (ViewHolder) convertView.getTag();
		}
		
		Contact contact = getItem(position);
		hodler.name.setText(contact.name);
		hodler.phonenumber.setText(contact.number);
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView name;
		TextView phonenumber;
	}
}

