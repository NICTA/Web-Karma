package edu.isi.karma.controller.command.alignment;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;

import edu.isi.karma.controller.command.Command;
import edu.isi.karma.controller.command.JSONInputCommandFactory;
import edu.isi.karma.controller.history.HistoryJsonUtil;
import edu.isi.karma.rep.Workspace;
import edu.isi.karma.webserver.KarmaException;

public class GetSemanticSuggestionsCommandFactory
		extends
			JSONInputCommandFactory {

	private enum Arguments {
		worksheetId, hNodeId
	}
	
	@Override
	public Command createCommand(JSONArray inputJson, Workspace workspace)
			throws JSONException, KarmaException {
		String hNodeId = HistoryJsonUtil.getStringValue(Arguments.hNodeId.name(), inputJson);
		String worksheetId = HistoryJsonUtil.getStringValue(Arguments.worksheetId.name(), inputJson);
		return new GetSemanticSuggestionsCommand(getNewId(workspace), worksheetId, hNodeId);
	}

	@Override
	public Command createCommand(HttpServletRequest request, Workspace workspace) {
		String hNodeId = request.getParameter(Arguments.hNodeId.name());
		String worksheetId = request.getParameter(Arguments.worksheetId.name());
		return new GetSemanticSuggestionsCommand(getNewId(workspace), worksheetId, hNodeId);
	}

	@Override
	public Class<? extends Command> getCorrespondingCommand() {
		return GetSemanticSuggestionsCommand.class;
	}

}
