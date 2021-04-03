package floppaware.client.modules;

import floppaware.api.eventapi.PogEvent;
import floppaware.api.events.TickEvent;
import floppaware.api.module.Category;
import floppaware.api.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

// or why you shouldnt follow wurst+2's coding style
public class AntiEndCrystal extends Module
{
	public AntiEndCrystal( )
	{
		super( "AntiEndCrystal", "(depressive edition) disconnects you when you are near an end crystal. useful for logging.", -1, Category.Combat, true );
	}
	
	@PogEvent
	public void function_tickevent_event( TickEvent instance_of_event )
	{
		if( get_currently_loaded_entities_in_current_world( ) == null )
		{
			return;
		}
		
		for( Entity instance_of_each_entity_from_list : get_currently_loaded_entities_in_current_world( ) )
		{
			if( instance_of_each_entity_from_list != null && check_is_entity_an_end_crystal( instance_of_each_entity_from_list ) )
			{
				if( check_should_disconnect_because_an_end_crystal_entity_is_near_the_local_player_entity( instance_of_each_entity_from_list ) )
				{
					disconnect_from_current_server_with_custom_reason( Formatting.GRAY + "[ anti_end_crystal ] you were near an end crystal." );
					break;
				}
			}
		}
	}
	
	Iterable< Entity > get_currently_loaded_entities_in_current_world( )
	{
		if( get_mine_craft_client_instance( ).world == null )
		{
			return null;
		}
		return get_mine_craft_client_instance( ).world.getEntities( );
	}
	
	boolean check_is_entity_an_end_crystal( Entity instance_of_entity )
	{
		return instance_of_entity instanceof EndCrystalEntity;
	}
	
	boolean check_should_disconnect_because_an_end_crystal_entity_is_near_the_local_player_entity( Entity instance_of_the_entity )
	{
		if( get_mine_craft_client_instance( ).player == null )
		{
			return false;
		}
		if( instance_of_the_entity == null )
		{
			return false;
		}
		double squared_distance_from_local_player_entity_to_end_crystal_entity = get_squared_distance_from_one_entity_to_another_entity( get_mine_craft_client_instance( ).player, instance_of_the_entity );
		return squared_distance_from_local_player_entity_to_end_crystal_entity <= 4;
	}
	
	double get_squared_distance_from_one_entity_to_another_entity( Entity instance_of_first_entity, Entity instance_of_second_entity )
	{
		if( instance_of_first_entity == null )
		{
			return 0.00000D;
		}
		if( instance_of_second_entity == null )
		{
			return 0.00000D;
		}
		return instance_of_first_entity.squaredDistanceTo( instance_of_second_entity );
	}
	
	void disconnect_from_current_server_with_custom_reason( String str_reason )
	{
		if( get_mine_craft_client_instance( ).player == null )
		{
			return;
		}
		if( get_mine_craft_client_instance( ).player.networkHandler == null )
		{
			return;
		}
		if( get_mine_craft_client_instance( ).player.networkHandler.getConnection( ) == null )
		{
			return;
		}

		get_mine_craft_client_instance( ).player.networkHandler.getConnection( ).disconnect( new LiteralText( str_reason ) );
	}
	
	MinecraftClient get_mine_craft_client_instance( )
	{
		return MinecraftClient.getInstance( );
	}
}
