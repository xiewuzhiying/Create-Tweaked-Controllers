package com.getitemfromblock.create_tweaked_controllers.input;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class JoystickButtonInput implements GenericInput
{
    private int buttonID = -1;
    private boolean invertValue = false;

    public JoystickButtonInput(int buttonID)
    {
        this.buttonID = buttonID;
    }

    public JoystickButtonInput()
    {
    }

    @Override
    public boolean GetButtonValue()
    {
        return invertValue ? !JoystickInputs.GetButton(buttonID) : JoystickInputs.GetButton(buttonID);
    }

    @Override
    public float GetAxisValue()
    {
        return GetButtonValue() ? 1.0f : 0.0f;
    }

    @Override
    public String GetDisplayName()
    {
        return "Joystick button " + buttonID;
    }

    @Override
    public boolean IsInputValid()
    {
        return buttonID < JoystickInputs.GetButtonCount() && buttonID >= 0;
    }

    @Override
    public void Serialize(DataOutputStream buf) throws IOException
    {
        buf.writeBoolean(invertValue);
        buf.writeInt(buttonID);
    }

    @Override
    public void Deserialize(DataInputStream buf) throws IOException
    {
        invertValue = buf.readBoolean();
        buttonID = buf.readInt();
    }

    @Override
    public InputType GetType()
    {
        return InputType.JOYSTICK_BUTTON;
    }

    @Override
    public int GetValue()
    {
        return buttonID;
    }
    
}
